import java.util.Scanner;

public class Admin {

    private Scanner scanner;
    private SalesReport salesReport;

    public Admin() {
        scanner = new Scanner(System.in);
        salesReport = new SalesReport();
    }

    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("           ADMIN MENU");
            System.out.println("================================");
            System.out.println("1. View Tables");
            System.out.println("2. View Menu");
            System.out.println("3. View Orders");
            System.out.println("4. Update Order Status");
            System.out.println("5. Daily Sales Report");
            System.out.println("6. Back");
            System.out.println("================================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    viewTables();
                    break;

                case 2:
                    viewMenu();
                    break;

                case 3:
                    viewOrders();
                    break;

                case 4:
                    updateOrderStatus();
                    break;

                case 5:
                    salesReport.displayReport();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void viewTables() {

        System.out.println();
        System.out.println("========== TABLE STATUS ==========");
        System.out.println("Table 1 : Available");
        System.out.println("Table 2 : Available");
        System.out.println("Table 3 : Occupied");
        System.out.println("Table 4 : Available");
        System.out.println("Table 5 : Occupied");
    }

    private void viewMenu() {

        System.out.println();
        System.out.println("========== RESTAURANT MENU ==========");
        System.out.println("101. French Fries       Rs.120");
        System.out.println("102. Chicken 65         Rs.180");
        System.out.println("201. Chicken Biryani    Rs.250");
        System.out.println("202. Veg Fried Rice     Rs.160");
        System.out.println("301. Coffee             Rs.70");
    }

    private void viewOrders() {

        System.out.println();
        System.out.println("========== ORDERS ==========");
        System.out.println("No orders available yet.");
    }

    private void updateOrderStatus() {

        System.out.println();
        System.out.println("========== ORDER STATUS ==========");
        System.out.println("1. PLACED");
        System.out.println("2. PREPARING");
        System.out.println("3. READY");
        System.out.println("4. SERVED");
        System.out.println();
        System.out.println("Order status module will be connected later.");
    }
}
