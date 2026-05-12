#Function to calculate weighted average
def calculate_avera(prelim, midterm, finals):
    return (prelim * 0.30) + (midterm * 0.30) + (finals * 0.40)

#Input grades
prelim = float(input("Enter your prelim grade:"))
midterm = float(input("Enter your midterm grade:"))
finals = float(input("Enter your finals grade:"))

#Calculate average
average = calculate_avera(prelim, midterm, finals)

#DIsplay result
average = print(f"Your average grade is: {average}")


