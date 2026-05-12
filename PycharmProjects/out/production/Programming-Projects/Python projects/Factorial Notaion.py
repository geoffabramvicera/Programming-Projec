num = int(input("Enter a number: "))
fn = 1

for i in range(1, num + 1):
    result = fn = fn * i

print(f"The Factorial of number {num} is {result}")