num = int(input("Enter a number: "))
lastNumber = num + 1
for row in range(1, lastNumber):
    for column in range(1, row + 1):
        print(column, end=' ')
    print("")