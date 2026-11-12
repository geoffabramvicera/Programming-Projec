from flask import Flask, request, jsonify, send_from_directory
from flask_cors import CORS
from pathlib import Path
import re
import joblib

# Find the project folder
BASE_DIR = Path(__file__).resolve().parent

app = Flask(__name__)
CORS(app)

# Load the trained model
model = joblib.load(BASE_DIR / 'phishing_model.pkl')


# Display the original HTML website
@app.route('/')
def home():
    return send_from_directory(BASE_DIR, 'index.html')


# Extract 10 features from a URL
def extract_features(url):
    return {
        'length': len(url),
        'dots': url.count('.'),
        'hyphens': url.count('-'),
        'slashes': url.count('/'),
        'underscores': url.count('_'),
        'at_sign': url.count('@'),
        'https': 1 if url.startswith('https') else 0,
        'digits': sum(c.isdigit() for c in url),
        'subdomains': url.count('.') - 1,
        'has_ip': 1 if re.match(r'\d+\.\d+\.\d+\.\d+', url) else 0
    }


# Receive a URL and predict whether it is phishing
@app.route('/predict', methods=['POST'])
def predict():
    data = request.json
    url = data.get('url', '')

    # Extract URL features
    features = extract_features(url)

    # Convert features into the correct order
    feature_values = [
        features['length'],
        features['dots'],
        features['hyphens'],
        features['slashes'],
        features['underscores'],
        features['at_sign'],
        features['https'],
        features['digits'],
        features['subdomains'],
        features['has_ip']
    ]

    # Make prediction
    prediction = model.predict([feature_values])[0]
    probability = model.predict_proba([feature_values])[0][1]

    # Return the result
    return jsonify({
        'url': url,
        'is_phishing': bool(prediction),
        'confidence': round(float(probability) * 100, 2),
        'features': features
    })


# Start Flask
if __name__ == '__main__':
    app.run(host='127.0.0.1', debug=True, port=5000)
