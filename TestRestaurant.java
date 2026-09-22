import java.util.Scanner;

public class TestRestaurant {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CustomerManager customerManager = new CustomerManager();
        TableManager tableManager = new TableManager();

        System.out.println("======================================");
        System.out.println("     RESTAURANT MANAGEMENT SYSTEM");
        System.out.println("======================================");
        // Customer registration
        System.out.println("\n---------- CUSTOMER REGISTRATION ----------");

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        System.out.print("Enter number of guests: ");
        int guests = sc.nextInt();

        Customer customer = customerManager.registerCustomer(
                name,
                phone,
                guests
        );

        // Stop if registration failed
        if (customer == null) {
            System.out.println("\nCustomer registration failed.");
            sc.close();
            return;
        }

        System.out.println("\nCustomer registered successfully!");

        // Show suitable tables
        tableManager.displayAvailableTables(guests);

        // Select table
        System.out.print("\nEnter table number to reserve: ");
        int tableNumber = sc.nextInt();

        // Reserve table
        boolean reserved = tableManager.reserveTable(
                tableNumber,
                guests
        );

        // Assign table to customer
        if (reserved) {

            customer.setTableNumber(tableNumber);

            System.out.println("\nReservation completed!");

            customer.displayCustomer();
        } else {
            System.out.println("\nReservation failed.");
        }

        // Show all tables
        tableManager.displayAllTables();

        // Release table
        if (reserved) {

            System.out.print(
                    "\nDo you want to release the table? (yes/no): "
            );

            sc.nextLine();

            String choice = sc.nextLine();

            if (choice.equalsIgnoreCase("yes")) {

                tableManager.releaseTable(tableNumber);

                customer.setTableNumber(-1);

                System.out.println(
                        "\nTable released successfully!"
                );

                customer.displayCustomer();

                tableManager.displayAllTables();
            }
        }

        sc.close();
    }
}
