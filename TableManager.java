import java.util.ArrayList;

public class TableManager {

    private ArrayList<Table> tables;

    public TableManager() {

        tables = new ArrayList<>();

        tables.add(new Table(1, 2));
        tables.add(new Table(2, 2));
        tables.add(new Table(3, 4));
        tables.add(new Table(4, 4));
        tables.add(new Table(5, 6));
        tables.add(new Table(6, 8));
    }

    public void displayAllTables() {

        System.out.println("\n========== TABLE STATUS ==========");

        for (Table table : tables) {
            table.displayTable();
        }
    }

    public void displayAvailableTables(int numberOfGuests) {

        System.out.println("\n====== AVAILABLE TABLES ======");

        boolean found = false;

        for (Table table : tables) {

            if (!table.isOccupied()
                    && table.getCapacity() >= numberOfGuests) {

                table.displayTable();
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                "No suitable table available for "
                + numberOfGuests + " guests."
            );
        }
    }

    public boolean reserveTable(int tableNumber, int numberOfGuests) {

        for (Table table : tables) {

            if (table.getTableNumber() == tableNumber) {

                if (table.isOccupied()) {
                    System.out.println("Table is already occupied.");
                    return false;
                }

                if (table.getCapacity() < numberOfGuests) {
                    System.out.println(
                        "Table capacity is not enough for "
                        + numberOfGuests + " guests."
                    );
                    return false;
                }

                table.reserveTable();

                System.out.println(
                    "Table " + tableNumber +
                    " reserved successfully!"
                );

                return true;
            }
        }

        System.out.println("Table not found.");
        return false;
    }

    public void releaseTable(int tableNumber) {

        for (Table table : tables) {

            if (table.getTableNumber() == tableNumber) {

                if (!table.isOccupied()) {
                    System.out.println("Table is already available.");
                    return;
                }

                table.releaseTable();

                System.out.println(
                    "Table " + tableNumber +
                    " is now available."
                );

                return;
            }
        }

        System.out.println("Table not found.");
    }
}
