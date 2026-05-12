num1 = int(input("Enter 1st number: "))
num2 = int(input("Enter 2nd number: "))
operator = input("Enter operator (+ - x /): ")

sum = num1 + num2
difference = num1 - num2
product = num1 * num2
quotient = num1 / num2

if operator == "+":
    print(f"{sum}")
elif operator == "-":
    print(f"{difference}")
elif operator == "x":
    print(f"{product}")
elif operator == "/":
    print(f"{quotient}")
else:
    print("Invalid Operator")