num1 = int(input("Enter a number: "))
operator = input("Enter operator (+ - x / ): ")
num2 = int(input("Enter a number: "))
operator2 = input("Enter operator (+ - x / =): ")
equals = 0

while not operator2 == "=":
    num3 = int(input("Enter a number: "))
    operator = input("Enter operator (+ - x / ): ")

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


while not operator == "=":
    equals =+ int(input("Enter a " + str(num2 + 1) + " number: "))
    num2 += 1

print("Result =", sum or difference or product or quotient)

