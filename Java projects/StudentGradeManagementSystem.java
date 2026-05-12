import java.util.Scanner;

public class StudentGradeManagementSystem {
    public static void main(String [] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your grade in subject 1: ");
        int subject1 = scanner.nextInt();
        
        System.out.print("Enter your grade in subject 2: ");
        int subject2 = scanner.nextInt();

        System.out.print("Enter your grade in subject 3: ");
        int subject3 = scanner.nextInt();

        System.out.print("Enter your grade in subject 4: ");
        int subject4 = scanner.nextInt();

        // Calculate the average, highest, and lowest grades
        float gwa = (subject1 + subject2 + subject3 + subject4)/4;
        int highest_grade = Math.max(Math.max(subject1, subject2), Math.max(subject3, subject4));
        int lowest_grade = Math.min(Math.min(subject1, subject2), Math.min(subject3, subject4));

        System.out.println();
        System.out.println("Student Information");
        System.out.println("---------------");
        System.out.println("Full Name: " + name);
        System.out.println("Your highest grade: " + highest_grade);
        System.out.println("Your lowest grade is: " + lowest_grade);
        System.out.println("Your average grade is: " + gwa);
        
        scanner.close();
    }
}
