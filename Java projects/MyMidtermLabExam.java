import java.util.Scanner;

public class MyMidtermLabExam {
    static String[] issueDescription = new String[5];
    static String[] urgencyLevel = new String[5];
    static String[] status = new String[5];
    static int totalTickets = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            mainMenu();
        }
    }

    public static void mainMenu() {
        System.out.println();
        System.out.println("=== IT Ticket System ===");
        System.out.println("1. Add Ticket");
        System.out.println("2. Update Ticket Status");
        System.out.println("3. Show All Tickets");
        System.out.println("4. Generate Report");
        System.out.println("5. Exit");
        System.out.print("Enter Choice: ");

        int enterChoice = scanner.nextInt();
        scanner.nextLine();

        switch (enterChoice) {
            case 1:
                addTicket();
                break;
            case 2:
                updateTicketStatus();
                break;
            case 3:
                showTickets();
                break;
            case 4:
                generateReport();
                break;
            case 5:
                System.out.println("Exiting... Thank you!");
                System.exit(0);
            default:
                System.out.println("Invalid input. Please enter a number 1-5.");
                return;
        }
    }

    public static void addTicket(){
        if (totalTickets < 5) {
            System.out.print("Enter issue description: ");
            String issue = scanner.nextLine();

            System.out.print("Enter urgency (Low / Medium / High): ");
            String urgency = scanner.nextLine();

            issueDescription[totalTickets] = issue;
            urgencyLevel[totalTickets] = urgency;
            status[totalTickets] = "Pending";
            totalTickets = totalTickets + 1;

            System.out.println("Ticket successfully added!");
        } else {
            System.out.println("Maximum number of ticket reached.");
        }
    }

    public static void updateTicketStatus() {
        System.out.print("Enter ticket number to update: ");
        int ticketNum = scanner.nextInt();
        scanner.nextLine();
        int num = 0;

        switch(ticketNum) {
            case 1:
                num = 1;
                break;
            case 2:
                num = 2;
                break;
            case 3:
                num = 3;
                break;
            case 4:
                num = 4;
                break;
            case 5:
                num = 5;
                break;
            default:
                System.out.println("Invalid input. Please enter a number 1-5.");
                return;
        }

        if (num <= totalTickets) {
            if (status[num - 1].equals("Resolved")) {
                System.out.println("This ticket is already resolved.");
            } else {
                System.out.print("Enter new status (In Progress / Resolved): ");
                String newStatus = scanner.nextLine();
                if (newStatus.equals("In Progress") || newStatus.equals("Resolved")) {
                    status[num - 1] = newStatus;
                    System.out.println("Ticket status updated.");
                } else {
                    System.out.println("Invalid status.");
                }
            }
        } else {
            System.out.println("Ticket does not exist.");
        }
    }

    public static void showTickets() {
        System.out.println("--- All Tickets ---");
        for (int i = 0; i < totalTickets; i++) {
            System.out.println((i + 1) + ". [" + urgencyLevel[i] + "] "+ issueDescription[i] + " - Status: "  + status[i]);
        }
    }

    public static void generateReport() {
        int pending = 0;
        int resolved = 0;

        for (int i = 0; i < totalTickets; i++) {
            if (status[i].equals("Resolved")) {
                resolved = resolved + 1;
            } else {
                pending = pending + 1;
            }
        }
        
        System.out.println();
        System.out.println("--- Ticket Report ---");
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Pending Tickets: " + pending);
        System.out.println("Resolved Tickets: " + resolved);
    }
}