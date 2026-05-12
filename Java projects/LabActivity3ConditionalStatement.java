import java.util.Scanner;

    public class LabActivity3ConditionalStatement {
        public static void main(String[] args) {
        
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter your last name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter your age: ");
            int age = scanner.nextInt();

            //Conditional statement for age
            if (age < 18) {
                System.out.println("Minors are not allowed");
                System.exit(0);
            }
            else if (age > 65) {
                System.out.println("Senior Citizens are not allowed");
                System.exit(0);
            }
            

            System.out.print("Enter hours worked: ");
            float No_of_hours_worked = scanner.nextFloat();

            //Conditional statement for hoursWorked
            if (No_of_hours_worked > 24) {
                System.out.println("Number of hours worked cannot exceed 24 hours");
                System.exit(0);
            }
            else if (No_of_hours_worked <= 0) {
                System.out.println("Wrong input on daily work hours");
                System.exit(0);
            }


            System.out.print("Enter hourly wage: ");
            float hourly_wage = scanner.nextFloat();

            System.out.print("Enter role code (1-Manager, 2-Supervisor, 3-Staff, 4-Intern): ");
            int roleCode = scanner.nextInt();

            //Conditional statement for role code
            String roleNumber;
            switch (roleCode) {
                case 1:
                    roleNumber = "Manager";
                    break;
                case 2:
                    roleNumber = "Supervisor";
                    break;     
                case 3:
                    roleNumber = "Staff";
                    break;
                case 4:
                    roleNumber = "Intern";
                    break;
                default:
                    roleNumber = "Undefined";
            }

            // Strings to uppercase
            String uppercase_firstName = firstName.toUpperCase();
            String uppercase_lastName = lastName.toUpperCase();
        
            // Calculate the user's input
            int government_mandated_benefits = 1500;
            int years_to_retirement = 65 - age;
            int abs_value = Math.abs(years_to_retirement);
            double daily_wage = No_of_hours_worked * hourly_wage;
            double rounded_value_of_daily_wage = Math.round(daily_wage);
            double weekly_wage = rounded_value_of_daily_wage * 5;
            double monthly_wage = weekly_wage * 4;
            double gross_yearly_wage = monthly_wage * 12;
            double thirty_two_percent = gross_yearly_wage * 0.32;
            double cutted_net_yearly_wage = gross_yearly_wage - government_mandated_benefits;
            double net_yearly_wage = gross_yearly_wage - thirty_two_percent - government_mandated_benefits;


            System.out.println();
            System.out.println("Employee Information");
            System.out.println("--------------------");
            System.out.println("Full Name:             " + uppercase_lastName + ", " + uppercase_firstName);
            System.out.println("Age:                   " + age + " years old");
            System.out.println("Position               " + roleNumber);
            System.out.println("Years to Retirement:   " + abs_value + " years");
            System.out.println("Daily Salary:          PHP " + rounded_value_of_daily_wage);
            System.out.println("Weekly Salary:         PHP " + weekly_wage);
            System.out.println("Monthly Salary:        PHP " + monthly_wage);
            System.out.println("Gross Yearly Salary:   PHP " + gross_yearly_wage);

            if (net_yearly_wage > 250000) {
                System.out.println("Net Yearly salary:     PHP " + net_yearly_wage);
            }
            else if (net_yearly_wage <= 250000) {
                System.out.println("Net Yearly salary:     PHP " + cutted_net_yearly_wage);
            }

            scanner.close();
    }
}