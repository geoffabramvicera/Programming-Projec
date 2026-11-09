# PhishGuard Simplified Edition, Change Report

## Final folder contents

The ZIP contains three main code files and three required supporting files:

| File | Why it remains |
|---|---|
| `app.py` | Flask web server, URL checks, feature extraction, and model predictions |
| `train_model.py` | Trains and evaluates the machine learning model |
| `index.html` | Simple web interface |
| `requirements.txt` | Python packages needed to run and train the system |
| `phishing_dataset.csv` | Training examples, required when training a new model |
| `public_suffix_list.dat` | Domain suffix data used to identify registered domains correctly |

After training, `train_model.py` creates `phishing_model.pkl`. That model file is needed by `app.py` at runtime, but it was not included because a full model could not be generated during this verification.

## What changed

### 1. `app.py`

The original backend relied on two extra Python modules, `url_features.py` and `model_pipeline.py`. Their required functions have been moved into `app.py`, so the backend now uses one main Python file.

The simplified file now handles:

- Loading the public suffix list and finding registered domains.
- Reading and validating a URL.
- Extracting numeric URL features, including URL length, hostname length, subdomains, path depth, query parameters, IP addresses, punycode, suspicious terms, and brand patterns.
- Converting the URL into character patterns for the classifier.
- Loading the saved model.
- Serving `index.html` and handling `/health` and `/predict` requests.
- Keeping the exact HTTPS homepage recognition rule for a small list of major domains, and returning a review warning when a listed domain has a suspicious model prediction.

The old API returned a larger technical breakdown. The simplified API returns the URL, prediction, score, result label, and review flags because the new page only needs those values.

### 2. `train_model.py`

The training code was simplified and now imports the URL helper functions from `app.py` rather than from separate modules.

Changes include:

- Uses direct loops and descriptive names instead of type annotations and compact expressions.
- Uses `GroupShuffleSplit` to create one 80/20 train/test split grouped by registered domain. This keeps the same registered domain out of both groups.
- Uses `DictVectorizer` for numeric URL features and `HashingVectorizer` for URL character patterns.
- Joins those feature matrices and trains an `SGDClassifier`.
- Prints accuracy, confusion matrix, precision, recall, and F1 results.
- Saves the model and vectorizers together in `phishing_model.pkl`.

The feature representation and classifier are simpler than the previous multi-vectorizer pipeline. Retraining is required. Accuracy should be checked using the full dataset before presenting final performance claims.

### 3. `index.html`

The original page was 782 lines. It has been replaced with a 106-line page containing:

- PhishGuard title and a short explanation.
- One URL input.
- One Check URL button.
- A simple backend status message.
- A result panel showing the verdict and phishing score.
- A short warning that a low score does not guarantee safety.

The frontend JavaScript uses `textContent` for user-provided values instead of inserting the submitted URL as HTML.

### 4. `requirements.txt`

Removed `Flask-Cors` because the page is now served by Flask from the same origin as the API. The remaining packages are Flask, joblib, NumPy, pandas, scikit-learn, and SciPy.

## Removed files and folders

These files were removed from the runnable ZIP because the new three-file structure does not use them:

- `url_features.py`, its functions were moved to `app.py`.
- `model_pipeline.py`, its matrix-building work was moved into `app.py` and `train_model.py`.
- `test_model_pipeline.py` and `test_url_features.py`, the old tests refer to the removed modules and would need to be rewritten for this version.
- `browser_extension/`, including the separate popup interface, manifest, and icons. This simplified edition uses the `index.html` web interface instead.
- `README.md`, `SECURITY.md`, `CONTRIBUTING.md`, `THIRD_PARTY_NOTICES.md`, `DATASET.md`, `.gitignore`, `.gitattributes`, and the `docs/` folder. These are not required to launch the local web system.

## How to run

Open a terminal in the folder containing `app.py`, then run:

```bash
python -m pip install -r requirements.txt
python train_model.py
python app.py
```

Open this address in your browser:

```text
http://127.0.0.1:5000
```

Run training before starting the app. Training creates `phishing_model.pkl` in the same folder.

## Verification performed

- Python syntax compilation for `app.py` and `train_model.py`: passed.
- Browser JavaScript syntax check with Node.js: passed.
- URL feature extraction across all 253,098 dataset rows: passed with zero extraction errors.
- Registered-domain grouping on the complete dataset: 253,098 URLs produced 101,033 domain groups. The checked 80/20 split had 210,999 training rows, 42,099 test rows, and zero shared registered domains.
- End-to-end training test on a random 12,000-row sample: passed. Sample accuracy was 81.23%, with no shared registered domains. This is a sample result, not the final full-dataset score.
- Prediction logic, invalid URL response, and recognized homepage behavior using the sample model: passed.

## Important limitations

The full 253,098-row training run was attempted but did not finish within the available execution window. Therefore, this delivery does not include a trained `phishing_model.pkl`, and full-dataset accuracy has not been verified. Flask is also not installed in the verification environment, so the actual HTTP server could not be launched here. The project includes Flask in `requirements.txt` for installation on your computer.

The simplified edition removes the separate Brave/Chrome extension. Use the web page served by `app.py`.
