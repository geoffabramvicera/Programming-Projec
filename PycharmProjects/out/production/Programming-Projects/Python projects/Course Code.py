computer_science = "BSCS..........COMPUTER SCIENCE"
information_technology = "BSIT..........INFORMATION TECHNOLOGY"
hospitality_management = "BSHM..........HOSPITALITY MANAGEMENT"
business_management = "BSBM..........BUSINESS MANAGEMENT"

course_code = input("Enter your course: ")
if course_code == "BSCS":
    print(f"{computer_science}")
elif course_code == "BSIT":
    print(f"{information_technology}")
elif course_code == "BSHM":
    print(f"{hospitality_management}")
elif course_code == "BSBM":
    print(f"{business_management}")
else:
    print("Invalid Course")