from pathlib import Path
from urllib.parse import urlsplit
import ipaddress
import joblib
from flask import Flask, jsonify, request, send_from_directory

FOLDER = Path(__file__).resolve().parent
MODEL_FILE = FOLDER / "phishing_model.pkl"
SUFFIX_FILE = FOLDER / "public_suffix_list.dat"

SUSPICIOUS_WORDS = [
    "login", "verify", "account", "secure", "update",
    "password", "bank", "confirm", "signin", "payment"
]

PUBLIC_SUFFIXES = set()
if SUFFIX_FILE.exists():
    with open(SUFFIX_FILE, "r", encoding="utf-8") as file:
        for line in file:
            suffix = line.strip().lower()
            if suffix and not suffix.startswith(("//", "*.", "!")):
                PUBLIC_SUFFIXES.add(suffix)

app = Flask(__name__)
model = None


def get_url_parts(url):
    if "://" not in url:
        url = "http://" + url
    try:
        return urlsplit(url)
    except ValueError:
        return urlsplit("")


def get_group_domain(url):
    hostname = (get_url_parts(url).hostname or "").lower()
    try:
        hostname = hostname.encode("idna").decode("ascii")
    except UnicodeError:
        pass

    try:
        ipaddress.ip_address(hostname)
        return hostname
    except ValueError:
        pass

    labels = hostname.split(".")
    for position in range(len(labels)):
        if ".".join(labels[position:]) in PUBLIC_SUFFIXES:
            return hostname if position == 0 else ".".join(labels[position - 1:])

    return ".".join(labels[-2:]) if len(labels) >= 2 else hostname


def extract_features(url):
    parts = get_url_parts(url)
    hostname = (parts.hostname or "").lower()
    try:
        hostname = hostname.encode("idna").decode("ascii")
    except UnicodeError:
        pass

    try:
        ipaddress.ip_address(hostname)
        has_ip = 1
    except ValueError:
        has_ip = 0

    suspicious_count = sum(word in url.lower() for word in SUSPICIOUS_WORDS)

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


def load_model():
    global model
    if model is None:
        model = joblib.load(MODEL_FILE)
    return model


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


@app.route("/")
def home():
    return send_from_directory(FOLDER, "index.html")


@app.route("/health")
def health():
    try:
        load_model()
        return jsonify({"model_loaded": True})
    except Exception:
        app.logger.exception("Could not load the phishing model.")
        return jsonify({
            "model_loaded": False,
            "error": "Model not found or could not be loaded. Run train_model.py first."
        }), 500


@app.route("/predict", methods=["POST"])
def predict():
    data = request.get_json(silent=True) or {}
    if not isinstance(data, dict):
        return jsonify({"error": "Invalid request data."}), 400

    url = str(data.get("url", "")).strip()
    validation_error = check_url(url)
    if validation_error:
        return jsonify({"error": validation_error}), 400

    try:
        current_model = load_model()
        features = extract_features(url)
        feature_values = list(features.values())

        prediction = current_model.predict([feature_values])[0]
        probabilities = current_model.predict_proba([feature_values])[0]
        classes = list(current_model.classes_)

        legitimate_score = float(probabilities[classes.index(0)]) * 100
        phishing_score = float(probabilities[classes.index(1)]) * 100

        tree_predictions = [
            int(tree.predict([feature_values])[0])
            for tree in current_model.estimators_
        ]
        legitimate_votes = tree_predictions.count(0)
        phishing_votes = tree_predictions.count(1)
        result = "Potential phishing URL" if prediction == 1 else "No phishing indicators detected"

        return jsonify({
            "url": url,
            "is_phishing": bool(prediction),
            "confidence": round(phishing_score, 2),
            "phishing_score": round(phishing_score, 2),
            "legitimate_score": round(legitimate_score, 2),
            "result_label": result,
            "features": features,
            "tree_votes": {
                "legitimate": legitimate_votes,
                "phishing": phishing_votes,
                "total": len(tree_predictions)
            },
            "review_required": False
        })
    except Exception:
        app.logger.exception("An error occurred during URL prediction.")
        return jsonify({
            "error": "An error occurred while analyzing the URL. Check the Flask terminal."
        }), 500


if __name__ == "__main__":
    app.run(host="127.0.0.1", port=5001, debug=False)