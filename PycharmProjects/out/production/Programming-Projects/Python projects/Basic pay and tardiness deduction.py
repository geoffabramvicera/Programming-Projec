employee_name = input("Enter employee name: ")
rate_per_hour = float(input("Enter rate per hour: "))
total_hours_worked = float(input("Enter total hours worked: "))
tardiness_hours = float(input("Enter absences : "))

basic_pay = rate_per_hour * total_hours_worked
deduction_for_tardiness = tardiness_hours * rate_per_hour
tax = 0.10 * basic_pay
total_deductions = deduction_for_tardiness + tax
net_pay = basic_pay - total_deductions

print("\n--- Pay Summary for", employee_name, "---")
print(f"Basic Pay: P{basic_pay:.2f}")
print(f"Deduction for Tardiness: P{deduction_for_tardiness:.2f}")
print(f"Tax (10%): P{tax:.2f}")
print(f"Total Deductions: P{total_deductions:.2f}")
print(f"Net Pay: P{net_pay:.2f}")
print(f"Absences:", tardiness_hours)