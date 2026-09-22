public class Table {

    private int tableNumber;
    private int capacity;
    private boolean occupied;

    public Table(int tableNumber, int capacity) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.occupied = false;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void reserveTable() {
        occupied = true;
    }

    public void releaseTable() {
        occupied = false;
    }

    public void displayTable() {

        String status;

        if (occupied) {
            status = "OCCUPIED";
        } else {
            status = "AVAILABLE";
        }

        System.out.println(
            "Table " + tableNumber +
            " | Capacity: " + capacity +
            " | Status: " + status
        );
    }
}
