import pandas as pd
import joblib

from pathlib import Path
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score, classification_report
from sklearn.model_selection import GroupShuffleSplit
from app import extract_features, get_group_domain

# File locations
FOLDER = Path(__file__).resolve().parent
DATASET_FILE = FOLDER / "phishing_dataset.csv"
MODEL_FILE = FOLDER / "phishing_model.pkl"

def main():
    print("Loading phishing dataset...")

    # Read the dataset
    data = pd.read_csv(DATASET_FILE)
    data = data.dropna(subset=["url", "label"])
    data["url"] = data["url"].astype(str).str.strip()
    data["label"] = data["label"].astype(str).str.lower().str.strip()

    # Keep only valid labels
    data = data[data["label"].isin(["legitimate", "phishing"])]
    data = data[data["url"] != ""]
    print("Total URLs:", len(data))

    if data["label"].nunique() != 2:
        raise ValueError(
            "The dataset must contain legitimate and phishing URLs."
        )

    # Extract URL features and labels
    features = []
    labels = []
    domains = []

    print("Extracting URL features...")

    for url, label in zip(data["url"], data["label"]):
        url_features = extract_features(url)
        features.append(list(url_features.values()))
        domains.append(get_group_domain(url))

        if label == "phishing":
            labels.append(1)
        else:
            labels.append(0)

    # Separate training and testing data by domain
    print("Separating training and testing data...")

    splitter = GroupShuffleSplit(
        test_size=0.2,
        n_splits=1,
        random_state=42
    )

    train_rows, test_rows = next(
        splitter.split(features, labels, domains)
    )

    X_train = [features[i] for i in train_rows]
    X_test = [features[i] for i in test_rows]
    y_train = [labels[i] for i in train_rows]
    y_test = [labels[i] for i in test_rows]

    print("Training URLs:", len(X_train))
    print("Testing URLs:", len(X_test))

    # Train the Random Forest model
    print("Training the Random Forest model...")

    model = RandomForestClassifier(
        n_estimators=100,
        random_state=42,
        n_jobs=-1
    )

    model.fit(X_train, y_train)

    # Evaluate the model
    predictions = model.predict(X_test)
    accuracy = accuracy_score(y_test, predictions)

    print("\nModel Accuracy:", round(accuracy * 100, 2), "%")
    print("\nClassification Report:")

    print(classification_report(
        y_test,
        predictions,
        labels=[0, 1],
        target_names=["Legitimate", "Phishing"],
        zero_division=0
    ))

    # Check for domains appearing in both groups
    train_domains = set()
    for i in train_rows:
        train_domains.add(domains[i])

    test_domains = set()
    for i in test_rows:
        test_domains.add(domains[i])

    shared_domains = train_domains.intersection(test_domains)
    print("Shared domains:", len(shared_domains))

    # Save the trained model
    joblib.dump(model, MODEL_FILE)
    print("\nModel successfully saved as phishing_model.pkl")

if __name__ == "__main__":
    main()