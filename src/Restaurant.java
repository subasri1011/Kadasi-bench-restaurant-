import java.util.Scanner;

public class Restaurant {

    private Scanner scanner;
    private Admin admin;
    private SalesReport salesReport;

    public Restaurant() {
        scanner = new Scanner(System.in);
        admin = new Admin();
        salesReport = new SalesReport();
    }

    public void customerMenu() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("         CUSTOMER MENU");
            System.out.println("================================");
            System.out.println("1. Register Customer");
            System.out.println("2. Check Available Tables");
            System.out.println("3. View Menu");
            System.out.println("4. Place Order");
            System.out.println("5. View Order");
            System.out.println("6. Generate Bill");
            System.out.println("7. Payment");
            System.out.println("8. Back");
            System.out.println("================================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Customer Registration Module");
                    break;

                case 2:
                    System.out.println("Table Availability Module");
                    break;

                case 3:
                    System.out.println("Menu Module");
                    break;

                case 4:
                    System.out.println("Order Module");
                    break;

                case 5:
                    System.out.println("View Order Module");
                    break;

                case 6:
                    System.out.println("Billing Module");
                    break;

                case 7:
                    System.out.println("Payment Module");
                    break;

                case 8:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void adminMenu() {
        admin.showMenu();
    }

    public SalesReport getSalesReport() {
        return salesReport;
    }
}
