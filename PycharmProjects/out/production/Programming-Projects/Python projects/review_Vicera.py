def get_inputs():
    formula_to_compute = int(input("Select an option (1-2): "))
    major_radius = float(input("Enter the major radius (R): "))
    minor_radius = float(input("Enter the minor radius (r): "))
    return formula_to_compute, major_radius, minor_radius

def validate_inputs(major_radius, minor_radius):
    if minor_radius > major_radius:
        return False, "Minor radius (r) must be less than Major radius (R)."
    if minor_radius == 0 or major_radius == 0:
        return False, "Both radii must be greater than zero."
    return True, ""

def compute_surface_area(major_radius, minor_radius):
    return (2 * 3.14 * major_radius) * (2 * 3.14 * minor_radius)

def compute_volume(major_radius, minor_radius):
    return (2 * 3.14 * major_radius) * (3.14 * minor_radius ** 2)

def torus_calculator():
    print("Torus Calculator")
    print("1. Compute Surface Area")
    print("2. Compute Volume")

    formula_to_compute, major_radius, minor_radius = get_inputs()
    valid, error_message = validate_inputs(major_radius, minor_radius)

    if not valid:
        print(error_message)
        return torus_calculator()

    if formula_to_compute == 1:
        print(f"Surface Area of the torus is: {compute_surface_area(major_radius, minor_radius)}")
    elif formula_to_compute == 2:
        print(f"Volume of the Torus is: {compute_volume(major_radius, minor_radius)}")

    prompt1 = input("Do you want to compute another set? (yes/no): ")

    if prompt1.lower() == "yes":
        return torus_calculator()
    else:
        print("Exiting the program. Goodbye!")

torus_calculator()