import java.util.Scanner;

public class TestTable {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TableManager manager = new TableManager();

        System.out.println("========================================");
        System.out.println("     RESTAURANT TABLE MANAGEMENT");
        System.out.println("========================================");

        System.out.print("\nEnter number of guests: ");
        int guests = sc.nextInt();

        manager.displayAvailableTables(guests);

        System.out.print("\nEnter table number: ");
        int tableNumber = sc.nextInt();

        boolean reserved =
                manager.reserveTable(tableNumber, guests);

        if (reserved) {
            manager.displayAllTables();

            System.out.println("\n----- Releasing Table -----");

            manager.releaseTable(tableNumber);

            manager.displayAllTables();
        }

        sc.close();
    }
}
