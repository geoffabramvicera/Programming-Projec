Name = input("Enter your name:")
Math = int(input("Enter your grade in Math:"))
Filipino = int(input("Enter your grade in Filipino:"))
English = int(input("Enter your grade in English:"))
Science = int(input("Enter your grade in Science:"))
PE = int(input("Enter your grade in PE:"))

average = (Math + Filipino + English + Science + PE)/5

print(f"{Name}, ito ang katotohanan.")
print("Ang iyong GPA ay", average)

if average >= 100:
    print("ANG GALING MO!!!")
elif average >= 90:
    print("Malupet ka!")
elif average >= 80:
    print("Pasado ka!!")
elif average >= 70:
    print("Safe lang")
elif average < 70:
    print("ML pa")