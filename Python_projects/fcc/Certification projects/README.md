# Python Projects: FreeCodeCamp Certification Projects

This repository contains a curated set of Python projects covering OOP, data structures, and algorithmic logic.

## Project Directory

| Project | Concept | Description |
| :--- | :--- | :--- |
| **Budget App** | OOP & Data Management | Manages financial categories, tracks deposits/withdrawals, and generates a visual spending chart. |
| **Hash Table** | Data Structures | A custom implementation of a hash table for efficient key-value lookups and data storage. |
| **Polygon Area** | Inheritance | Calculates area, perimeter, and visual representations of rectangles and squares using inheritance. |
| **Config Manager** | Dictionary Manipulation | Handles user settings using CRUD (Create, Read, Update, Delete) operations on a dictionary. |
| **Tower of Hanoi** | Recursion | An algorithmic solution to the classic puzzle using recursive stack operations. |

---
*Maintained by Geoff Abram Vicera*

---

# Budget App

An object-oriented ledger system that tracks deposits, withdrawals, and transfers between categories.

## Implementation Details
- **Class-Based Architecture:** Uses a `Category` class to manage independent budgets.
- **Data Visualization:** Includes a `create_spend_chart` function that computes percentages and generates a text-based bar chart.
- **Formatting:** Custom `__str__` method for clean, column-aligned ledger reports.

---

# Hash Table

A custom implementation of a Hash Table to understand how key-value lookups function at a low level.

## Key Features
- **Hash Function:** Converts keys into integer indices using character order summation.
- **Collision Handling:** Uses nested dictionaries to manage hash collisions (multiple keys mapping to the same hash).
- **CRUD Operations:** Includes `add`, `remove`, and `lookup` methods for efficient data retrieval.

---

# Polygon Area Calculator

A geometry tool demonstrating the power of class inheritance.

## Logic Overview
- **Inheritance:** The `Square` class inherits from the `Rectangle` class, overriding setter methods to ensure side equality.
- **Geometry Methods:** Calculates area, perimeter, and diagonal length.
- **Visualization:** A `get_picture` method generates a visual representation of the rectangle using asterisks.

---

# User Configuration Manager

A utility to manage application settings (CRUD) using dictionary manipulation.

## Core Functionality
- **Data Integrity:** Normalizes keys and values to lowercase to prevent duplicates or lookup errors.
- **Management:** Provides clear functions for adding, updating, viewing, and deleting configuration states.
- **Status Reporting:** Returns status strings to provide feedback on the success or failure of setting operations.

---

# Tower of Hanoi Solver

A recursive solution to the classic Tower of Hanoi logic puzzle.

## The Algorithm
- **Recursive Decomposition:** Uses a "sandwich" pattern:
  1. Move the (n-1) stack to the auxiliary rod.
  2. Move the largest disk to the target rod.
  3. Move the (n-1) stack from auxiliary to target.
- **State Tracking:** Utilizes a logging system to capture the state of the rods after every move.