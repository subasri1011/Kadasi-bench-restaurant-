import java.util.Scanner;

public class TestCustomer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CustomerManager customerManager = new CustomerManager();

        System.out.println("================================");
        System.out.println("     CUSTOMER REGISTRATION");
        System.out.println("================================");

        System.out.print("\nEnter customer name: ");
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

        System.out.println("\nCustomer registered successfully!");

        customer.displayCustomer();

        System.out.println("\n--------------------------------");

        customerManager.displayAllCustomers();

        sc.close();
    }
}
