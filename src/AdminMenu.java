import java.util.Scanner;

public class AdminMenu {
    public static void generateAdminMenu(Scanner scanner) {
        boolean keepRunning = true;
        while (keepRunning) {
            try {
                System.out.println("1. View all Customers.");
                System.out.println("2. View all Rooms.");
                System.out.println("3. View all Reservations.");
                System.out.println("4. Add a Room.");
                System.out.println("5. Back to Main Menu.");
                int selection = Integer.parseInt(scanner.nextLine());

                if (selection == 1) {
                    System.out.println("This is admin1");
                } else if (selection == 2) {
                    System.out.println("This is admin2");
                } else if (selection == 3) {
                    System.out.println("This is admin3");
                } else if (selection == 4) {
                    System.out.println("This is admin4");
                } else if (selection == 5) {
                    keepRunning = false;
                } else {
                    System.out.println("Please enter a number between 1 and 5.\n");
                }
            } catch (Exception ex) {
                System.out.println("Please enter a number between 1 and 5.\n");
            }
        }
    }
}
