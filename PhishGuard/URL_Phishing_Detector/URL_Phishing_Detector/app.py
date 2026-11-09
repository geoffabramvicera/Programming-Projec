
from pathlib import Path
from urllib.parse import urlsplit
import ipaddress
import joblib
from flask import Flask, jsonify, request, send_from_directory

# File locations
FOLDER = Path(__file__).resolve().parent
MODEL_FILE = FOLDER / "phishing_model.pkl"
SUFFIX_FILE = FOLDER / "public_suffix_list.dat"

# Suspicious words commonly found in phishing URLs
SUSPICIOUS_WORDS = [
    "login", "verify", "account", "secure", "update",
    "password", "bank", "confirm", "signin", "payment"
]

# Load public domain suffixes
PUBLIC_SUFFIXES = set()
if SUFFIX_FILE.exists():
    with open(SUFFIX_FILE, "r", encoding="utf-8") as file:
        for line in file:
            suffix = line.strip().lower()
            if suffix and not suffix.startswith(("//", "*.", "!")):
                PUBLIC_SUFFIXES.add(suffix)

app = Flask(__name__)
model = None

# Separate the URL into its parts
def get_url_parts(url):
    if "://" not in url:
        url = "http://" + url
    try:
        return urlsplit(url)
    except ValueError:
        return urlsplit("")

# Find the registered domain
def get_group_domain(url):
    parts = get_url_parts(url)
    hostname = (parts.hostname or "").lower()
    try:
        hostname = hostname.encode("idna").decode("ascii")
    except UnicodeError:
        pass

    # Check whether the hostname is an IP address
    try:
        ipaddress.ip_address(hostname)
        return hostname
    except ValueError:
        pass

    labels = hostname.split(".")

    # Find the registered domain using the suffix list
    for position in range(len(labels)):
        suffix = ".".join(labels[position:])
        if suffix in PUBLIC_SUFFIXES:
            if position == 0:
                return hostname
            return ".".join(labels[position - 1:])

    # Use the last two parts if no suffix is found
    if len(labels) >= 2:
        return ".".join(labels[-2:])
    return hostname

# Extract 12 features from a URL
def extract_features(url):
    parts = get_url_parts(url)
    hostname = (parts.hostname or "").lower()
    try:
        hostname = hostname.encode("idna").decode("ascii")
    except UnicodeError:
        pass

    # Check for an IP address
    try:
        ipaddress.ip_address(hostname)
        has_ip = 1
    except ValueError:
        has_ip = 0

    # Count suspicious words
    suspicious_count = 0
    for word in SUSPICIOUS_WORDS:
        if word in url.lower():
            suspicious_count += 1

    # Return the features in dictionary form
    return {
        "length": len(url),
        "hostname_length": len(hostname),
        "dots": hostname.count("."),
        "hyphens": hostname.count("-"),
        "slashes": parts.path.count("/"),
        "query_parameters": parts.query.count("&") + int(bool(parts.query)),
        "https": int(parts.scheme.lower() == "https"),
        "digits": sum(char.isdigit() for char in url),
        "has_ip": has_ip,
        "at_sign": int("@" in url),
        "punycode": int("xn--" in hostname),
        "suspicious_words": suspicious_count
    }

# Load the trained model
def load_model():
    global model
    if model is None:
        model = joblib.load(MODEL_FILE)
    return model

# Check whether the submitted URL is valid
def check_url(url):
    if not url:
        return "Please enter a URL."
    if len(url) > 2048:
        return "The URL is too long."
    if not url.lower().startswith(("http://", "https://")):
        return "Start the URL with http:// or https://."
    if any(char.isspace() for char in url):
        return "The URL must not contain spaces."

    try:
        parts = urlsplit(url)
        if not parts.hostname:
            return "Please enter a complete website address."
        parts.port
    except ValueError:
        return "The URL format is invalid."

    return None

# Display the website
@app.route("/")
def home():
    return send_from_directory(FOLDER, "index.html")

# Check whether the model is ready
@app.route("/health")
def health():
    try:
        load_model()
        return jsonify({"model_loaded": True})
    except Exception:
        return jsonify({
            "model_loaded": False,
            "error": "Model not found. Run train_model.py first."
        })

# Receive and analyze a URL
@app.route("/predict", methods=["POST"])
def predict():
    data = request.get_json(silent=True)

    if not isinstance(data, dict):
        return jsonify({
            "error": "Send a JSON object containing a URL."
        }), 400

    url = data.get("url", "")
    if not isinstance(url, str):
        return jsonify({"error": "The URL must be text."}), 400

    url = url.strip()
    error = check_url(url)
    if error:
        return jsonify({"error": error}), 400

    try:
        current_model = load_model()
        features = extract_features(url)
        feature_values = list(features.values())

        prediction = current_model.predict([feature_values])[0]
        probabilities = current_model.predict_proba([feature_values])[0]

        phishing_index = list(current_model.classes_).index(1)
        score = round(float(probabilities[phishing_index]) * 100, 2)

        if prediction == 1:
            result = "Potential phishing URL"
        else:
            result = "No phishing indicators detected"

        return jsonify({
            "url": url,
            "is_phishing": bool(prediction),
            "confidence": score,
            "features": features,
            "phishing_score": score,
            "result_label": result,
            "review_required": False
        })

    except FileNotFoundError:
        return jsonify({
            "error": "Model not found. Run train_model.py first."
        }), 503

    except Exception:
        app.logger.exception("URL analysis failed")
        return jsonify({
            "error": "The URL could not be analyzed."
        }), 500

# Start the Flask server
if __name__ == "__main__":
    app.run(host="127.0.0.1", port=5001, debug=False)
