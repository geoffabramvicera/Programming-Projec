num = int(input("Enter a number that ends the fibonaci: "))
a, b = 0, 1

for x in range(num):
    print(a, end =" ")
    a, b = b, a + b