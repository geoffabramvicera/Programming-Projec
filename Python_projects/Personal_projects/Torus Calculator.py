print("Torus Calculator")
print("1. Compute Surface Area")
print("2. Compute Volume")

formula_to_compute = int(input("Select an option (1-2): " ))
major_radius = float(input("Enter the major radius (R): "))
minor_radius = float(input("Enter the minor radius (r): "))

if minor_radius > major_radius:
    print("Minor radius (r) must be less than Major radius (R). Please try again.")
    formula_to_compute = int(input("Select an option (1-2): " ))
    major_radius = float(input("Enter the major radius (R): "))
    minor_radius = float(input("Enter the minor radius (r): "))

if minor_radius == 0 or major_radius == 0:
    print("Both radii must be greater than zero. Please try again.")
    formula_to_compute = int(input("Select an option (1-2): " ))
    major_radius = float(input("Enter the major radius (R): "))
    minor_radius = float(input("Enter the minor radius (r): "))

surface_area = (2 *3.14 * major_radius) * (2 * 3.14 * minor_radius)
volume = (2 * 3.14 * major_radius) * (3.14 * minor_radius ** 2)

if formula_to_compute == 1:
    print(f"Surface Area of the torus is: {surface_area}")
elif formula_to_compute == 2:
    print(f"Volume of the Torus is: {volume}")

prompt1 = input("Do you want to compute another set? (yes/no): ")

if prompt1 == "yes":
    print("Torus Calculator")
    print("1. Compute Surface Area")
    print("2. Compute Volume")

    formula_to_compute = int(input("Select an option (1-2): " ))
    major_radius = float(input("Enter the major radius (R): "))
    minor_radius = float(input("Enter the minor radius (r): "))

if formula_to_compute < 1 or formula_to_compute > 2:
    formula_to_compute = int(input("Select an option (1-2): " ))
    major_radius = float(input("Enter the major radius (R): "))
    minor_radius = float(input("Enter the minor radius (r): "))

if minor_radius > major_radius:
    print("Minor radius (r) must be less than Major radius (R). Please try again.")
    formula_to_compute = int(input("Select an option (1-2): " ))
    major_radius = float(input("Enter the major radius (R): "))
    minor_radius = float(input("Enter the minor radius (r): "))

if minor_radius == 0 or major_radius == 0:
    print("Both radii must be greater than zero. Please try again.")
    formula_to_compute = int(input("Select an option (1-2): " ))
    major_radius = float(input("Enter the major radius (R): "))
    minor_radius = float(input("Enter the minor radius (r): "))

surface_area = (2 *3.14 * major_radius) * (2 * 3.14 * minor_radius)
volume = (2 * 3.14 * major_radius) * (3.14 * minor_radius ** 2)

if formula_to_compute == 1:
    print(f"Surface Area of the torus is: {surface_area}")
elif formula_to_compute == 2:
    print(f"Volume of the Torus is: {volume}")

if prompt1 == "no":
    print("Exiting the program. Goodbye!")