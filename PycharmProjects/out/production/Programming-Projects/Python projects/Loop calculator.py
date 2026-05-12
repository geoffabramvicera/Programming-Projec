num1 = float(input("Enter a number:"))
num2 = float(input("Enter a number:"))
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

while operator == "=":
    num3 = float(input("Enter a number: "))
    operator = input("Enter operator (+ - x /): ")
    
print("Bye")