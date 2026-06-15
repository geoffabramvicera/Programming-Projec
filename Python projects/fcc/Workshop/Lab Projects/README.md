# Python Projects Portfolio

A comprehensive collection of my Python certification projects, algorithmic challenges, and data structure implementations.

## Project Directory

| Project | Concept | Description |
| :--- | :--- | :--- |
## Project Directory

| Project | Concept | Description |
| :--- | :--- | :--- |
| **Budget App** | OOP & Data Management | Manages financial categories, tracks deposits/withdrawals, and generates a visual spending chart. |
| **Hash Table** | Data Structures | A custom implementation of a hash table for efficient key-value lookups. |
| **Polygon Area** | Inheritance | Calculates area, perimeter, and visual representations using inheritance. |
| **User Config Manager** | Dictionary Manipulation | Handles user settings using CRUD operations. |
| **Tower of Hanoi** | Recursion | An algorithmic solution to the puzzle using recursive stack operations. |
| **Caesar Cipher** | Cryptography | A tool for text encryption/decryption using rotational shifts. |
| **Discount Calculator** | Strategy Pattern | Calculates optimal pricing using abstract strategies. |
| **Email Simulator** | OOP | Models email systems with state management and user interactions. |
| **Linked List** | Data Structures | Manual implementation of node-based sequential data. |
| **Media Catalogue** | Inheritance | Organizes media collections with polymorphism and error handling. |
| **RPG Character Generator** | Input Validation | Creates balanced characters with stat checks and UI visualization. |
| **Salary Tracker** | Property Decorators | Manages employee data with encapsulated logic and validation. |

---

## Budget App
An object-oriented ledger system that tracks financial transactions across multiple categories.

**Implementation Details:**
* **Class Architecture:** Uses a `Category` class to independently manage deposits and withdrawals.
* **Data Visualization:** Includes a custom `create_spend_chart` that parses ledger data to generate a text-based bar chart.
* **Formatting:** Overrides `__str__` to output cleanly formatted, column-aligned financial reports.

---

## Hash Table
A custom implementation of a Hash Table to manage key-value pairs at a low level.

**Core Data Structure:**
* **Hash Function:** Converts string keys into integer indices using character order summation.
* **Collision Handling:** Uses nested dictionaries to manage hash collisions effectively.
* **CRUD Operations:** Implements custom `add`, `remove`, and `lookup` methods for efficient data retrieval.

---

## Polygon Area
A geometry tool demonstrating class inheritance.

**Logic Overview:**
* **Inheritance:** The `Square` class inherits from `Rectangle`, overriding setters to ensure uniform side lengths.
* **Geometry Methods:** Handles area, perimeter, and diagonal calculations.
* **Visualization:** Includes a `get_picture` method that uses string multiplication to render the shape visually.

---

## User Config Manager
A utility to manage application settings using dictionary manipulation.

**Core Functionality:**
* **Data Integrity:** Normalizes all keys and values to lowercase to prevent duplicates.
* **Management:** Provides distinct functions for creating, updating, viewing, and deleting configuration states.
* **Status Reporting:** Returns feedback strings to confirm the success or failure of setting operations.

---

## Tower of Hanoi
A recursive solver for the classic Tower of Hanoi logic puzzle.

**Algorithm:**
* **Recursive Decomposition:** Uses a "sandwich" recursion pattern to move disks: (n-1) to auxiliary, move largest to target, then (n-1) to target.
* **State Tracking:** Utilizes a logging system to capture and record the rod state after every individual move.

---

## Caesar Cipher
A cryptographic tool for text encryption and decryption using a rotational cipher.

**Logic Overview:**
* **String Translation:** Employs `str.maketrans` for high-performance character mapping rather than iterating through characters.
* **Cipher Logic:** Uses a single function that flips the shift sign to toggle between encrypt and decrypt modes.
* **Validation:** Enforces shift range constraints (1–25) to ensure valid cipher output.

---

## Discount Calculator
A system using the Strategy Pattern to determine optimal product pricing.

**Design Pattern:**
* **Strategy Pattern:** Decouples discount logic from the `DiscountEngine`, allowing for new discount rules without modifying existing code.
* **Abstract Base Classes:** Uses `ABC` to enforce required methods (`is_applicable`, `apply_discount`) across different strategies.
* **Dynamic Calculation:** Iterates through available strategies to find the minimum price for the user.

---

## Email Simulator
An object-oriented system that models email interactions between users.

**System Architecture:**
* **State Management:** Tracks message status (read vs. unread) within the `Email` object.
* **Inbox Logic:** A dedicated `Inbox` class manages list operations like receiving, listing, and deleting emails.
* **Encapsulation:** Uses `User` objects to hold the interaction logic, separating the "sender" functionality from the storage logic.

---

## Linked List
A fundamental data structure showcasing manual pointer management.

**Structural Implementation:**
* **Node-Based Architecture:** Defines a nested `Node` class to hold data and the `next` pointer.
* **Pointer Manipulation:** Handles head-node reassignments and list traversal to dynamically remove or append nodes.
* **Efficiency:** Demonstrates low-level memory management and sequential data organization.

---

## Media Catalogue
A robust organizational tool for media collections.

**Inheritance & Polymorphism:**
* **Class Hierarchy:** `TVSeries` inherits from the `Movie` parent class, extending core attributes.
* **Polymorphism:** The catalogue handles both media types seamlessly in a single list, using type-checking to filter data.
* **Exception Handling:** Implements custom `MediaError` exceptions to improve error reporting when adding invalid types.

---

## RPG Character Generator
A character profile utility with strict input validation.

**Character Logic & UI:**
* **Input Sanitization:** Uses conditional checks to ensure names (no spaces, length limits) and stats are valid.
* **UI Visualization:** Utilizes Unicode characters (`●` and `○`) to render a visual bar chart of stats for a clean CLI output.
* **Balance Enforcement:** Uses a "point-buy" system (summing to 7) to ensure character balance during creation.

---

## Salary Tracker
An advanced employee management system focusing on data integrity.

**Encapsulation & Decorators:**
* **Property Decorators:** Uses `@property` and `@setter` to control access to employee attributes.
* **Validation Logic:** Implements logic to prevent invalid state changes (e.g., banning level downgrades or salary cuts).
* **Automated Updates:** Automatically triggers salary recalculations when the employee's level changes.