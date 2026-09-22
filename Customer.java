public class Customer {

    private int customerId;
    private String name;
    private String phone;
    private int numberOfGuests;
    private int tableNumber;

    public Customer(int customerId, String name, String phone, int numberOfGuests) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.numberOfGuests = numberOfGuests;
        this.tableNumber = -1;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void displayCustomer() {

        System.out.println("\n========== CUSTOMER DETAILS ==========");
        System.out.println("Customer ID      : " + customerId);
        System.out.println("Name             : " + name);
        System.out.println("Phone            : " + phone);
        System.out.println("Number of Guests : " + numberOfGuests);

        if (tableNumber == -1) {
            System.out.println("Table            : Not Assigned");
        } else {
            System.out.println("Table            : " + tableNumber);
        }
    }
}
