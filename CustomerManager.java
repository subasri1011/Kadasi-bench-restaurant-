import java.util.ArrayList;

public class CustomerManager {

    private ArrayList<Customer> customers;
    private int nextCustomerId;

    public CustomerManager() {
        customers = new ArrayList<>();
        nextCustomerId = 101;
    }

    public Customer registerCustomer(
            String name,
            String phone,
            int numberOfGuests) {

        // Validate name
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Invalid name.");
            return null;
        }

        // Validate phone
        if (!phone.matches("\\d{10}")) {
            System.out.println("Invalid phone number. Enter 10 digits.");
            return null;
        }

        // Validate number of guests
        if (numberOfGuests <= 0 || numberOfGuests > 8) {
            System.out.println(
                "Invalid number of guests. Enter between 1 and 8."
            );
            return null;
        }

        Customer customer = new Customer(
                nextCustomerId,
                name,
                phone,
                numberOfGuests
        );

        customers.add(customer);
        nextCustomerId++;

        return customer;
    }

    public Customer findCustomer(int customerId) {

        for (Customer customer : customers) {

            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }

        return null;
    }

    public void displayAllCustomers() {

        System.out.println("\n========== ALL CUSTOMERS ==========");

        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
            return;
        }

        for (Customer customer : customers) {
            customer.displayCustomer();
        }
    }
}
