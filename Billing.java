import java.util.ArrayList;
import java.util.Scanner;

class OrderItem {
    private String name;
    private double price;
    private int quantity;

    OrderItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    String getName() {
        return name;
    }

    double getPrice() {
        return price;
    }

    int getQuantity() {
        return quantity;
    }

    double getTotal() {
        return price * quantity;
    }
}

public class Billing {

    static Scanner sc = new Scanner(System.in);

    static double totalSales = 0;
    static int completedOrders = 0;
    static int loyaltyPoints = 100;

    static double generateBill(ArrayList<OrderItem> order) {

        double subtotal = 0;

        System.out.println("\n==================================================");
        System.out.println("                 KADASI BENCH");
        System.out.println("              RESTAURANT BILL");
        System.out.println("          Last Bench, First Choice!");
        System.out.println("==================================================");

        System.out.printf("%-22s %-5s %-8s %-10s%n",
                "Item", "Qty", "Rate", "Amount");

        System.out.println("--------------------------------------------------");

        for (OrderItem item : order) {
            double amount = item.getTotal();
            subtotal += amount;

            System.out.printf(
                    "%-22s %-5d %-8.2f Rs.%-10.2f%n",
                    item.getName(),
                    item.getQuantity(),
                    item.getPrice(),
                    amount
            );
        }

        System.out.println("--------------------------------------------------");

        double cgst = subtotal * 0.025;
        double sgst = subtotal * 0.025;

        double discount = applyCoupon(subtotal);
        double pointsDiscount = redeemPoints();

        double total = subtotal + cgst + sgst
                - discount - pointsDiscount;

        if (total < 0) {
            total = 0;
        }

        System.out.printf("Subtotal            : Rs.%.2f%n", subtotal);
        System.out.printf("CGST 2.5%%           : Rs.%.2f%n", cgst);
        System.out.printf("SGST 2.5%%           : Rs.%.2f%n", sgst);
        System.out.printf("Coupon Discount     : Rs.%.2f%n", discount);
        System.out.printf("Points Redeemed     : Rs.%.2f%n", pointsDiscount);

        System.out.println("--------------------------------------------------");
        System.out.printf("NET BILL            : Rs.%.2f%n", total);
        System.out.println("==================================================");

        return total;
    }

    static double applyCoupon(double subtotal) {

        System.out.print("\nDo you have coupon? (y/n): ");
        String choice = sc.next();

        if (!choice.equalsIgnoreCase("y")) {
            return 0;
        }

        System.out.print("Enter coupon code: ");
        String code = sc.next();

        if (code.equalsIgnoreCase("FOOD10")) {
            double discount = subtotal * 0.10;

            System.out.println("Coupon applied!");
            System.out.printf("You saved Rs.%.2f%n", discount);

            return discount;
        }

        System.out.println("Invalid coupon code.");
        return 0;
    }

    static double redeemPoints() {

        System.out.println("\nAvailable Loyalty Points: " + loyaltyPoints);
        System.out.print("Redeem points? (y/n): ");
        String choice = sc.next();

        if (!choice.equalsIgnoreCase("y")) {
            return 0;
        }

        System.out.print("Enter points to redeem: ");
        int points = sc.nextInt();

        if (points <= 0 || points > loyaltyPoints) {
            System.out.println("Invalid points.");
            return 0;
        }

        double discount = points / 2.0;
        loyaltyPoints -= points;

        System.out.printf("Points discount: Rs.%.2f%n", discount);

        return discount;
    }

    static void splitBill(double total) {

        System.out.print("\nSplit bill? (y/n): ");
        String choice = sc.next();

        if (!choice.equalsIgnoreCase("y")) {
            return;
        }

        System.out.print("Number of people: ");
        int people = sc.nextInt();

        if (people <= 0) {
            System.out.println("Invalid number.");
            return;
        }

        double each = total / people;

        System.out.printf("Each person pays: Rs.%.2f%n", each);
    }

    static boolean payment(double total) {

        System.out.println("\n========== PAYMENT ==========");
        System.out.println("1. Cash");
        System.out.println("2. UPI");
        System.out.println("3. QR Code");
        System.out.println("4. Card");

        System.out.print("Choose payment method: ");
        String choice = sc.next();

        String method;

        switch (choice.toLowerCase()) {

            case "1":
            case "cash":
                method = "Cash";
                break;

            case "2":
            case "upi":
                method = "UPI";

                System.out.println("\nUPI ID: kadasibench@upi");
                System.out.printf("Amount: Rs.%.2f%n", total);

                System.out.print("Enter Transaction ID: ");
                sc.next();

                break;

            case "3":
            case "qr":
            case "qrcode":
                method = "QR Code";

                System.out.println("\n========== SCAN QR ==========");
                System.out.println("| []  [][]  []  [][] |");
                System.out.println("| [][]  [][][]  []   |");
                System.out.println("| [][][]   []  [][]  |");
                System.out.println("| [][]  [][]  [][][] |");

                System.out.println("UPI ID: kadasibench@upi");
                System.out.printf("Amount: Rs.%.2f%n", total);

                System.out.print("Enter Transaction ID: ");
                sc.next();

                break;

            case "4":
            case "card":
                method = "Card";

                System.out.print("Enter last 4 digits of card: ");
                sc.next();

                break;

            default:
                System.out.println("Invalid payment method.");
                return false;
        }

        System.out.println("\nPayment Successful!");
        System.out.println("Method : " + method);
        System.out.printf("Paid   : Rs.%.2f%n", total);

        totalSales += total;
        completedOrders++;

        return true;
    }

    static void loyaltyReward(double total) {

        int earned = (int) (total / 10);
        loyaltyPoints += earned;

        System.out.println("\n========== POINTS SUMMARY ==========");
        System.out.println("Points Earned    : " + earned);
        System.out.println("Available Points : " + loyaltyPoints);

        if (total >= 500) {
            System.out.println("Bonus: High-value order reward unlocked!");
        }
    }

    static void eBill(String customer, String mobile, double total) {

        String billId = "KB" + System.currentTimeMillis() % 100000;

        System.out.println("\n========== DIGITAL E-BILL ==========");
        System.out.println("Restaurant: Kadasi Bench");
        System.out.println("Bill ID   : " + billId);
        System.out.println("Customer  : " + customer);
        System.out.println("Mobile    : " + mobile);
        System.out.printf("Amount    : Rs.%.2f%n", total);

        System.out.println("\n========== WHATSAPP BILL PREVIEW ==========");
        System.out.println("Dear " + customer + ",");
        System.out.println("Thank you for dining at Kadasi Bench!");
        System.out.printf("Your bill amount is Rs.%.2f%n", total);
        System.out.println("Bill ID: " + billId);
        System.out.println(
                "E-Bill Link: kadasibench.local/bill/" + billId
        );
        System.out.println("We hope to see you again!");
    }

    static void feedback() {

        System.out.println("\n========== FEEDBACK ==========");
        System.out.print("Rate your experience (1-5): ");

        int rating = sc.nextInt();

        if (rating >= 4 && rating <= 5) {
            System.out.println("Thank you! Glad you enjoyed Kadasi Bench.");
        } else if (rating >= 1 && rating <= 3) {
            System.out.println("Thank you. We will improve your experience.");
        } else {
            System.out.println("Invalid rating.");
        }
    }

    static void salesReport() {

        System.out.println("\n========== DAILY SALES REPORT ==========");
        System.out.println("Restaurant       : Kadasi Bench");
        System.out.println("Completed Orders : " + completedOrders);
        System.out.printf("Total Sales      : Rs.%.2f%n", totalSales);
    }

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("              KADASI BENCH");
        System.out.println("       SMART RESTAURANT BILLING SYSTEM");
        System.out.println("        Last Bench, First Choice!");
        System.out.println("==============================================");

        System.out.print("Enter customer name: ");
        String customer = sc.nextLine();

        System.out.print("Enter mobile number: ");
        String mobile = sc.next();

        ArrayList<OrderItem> order = new ArrayList<>();

        // Demo order
        // Later Member 2 can pass actual customer order here
        order.add(new OrderItem("Chicken Biryani", 250, 2));
        order.add(new OrderItem("Fresh Lime Juice", 80, 2));

        double total = generateBill(order);

        splitBill(total);

        boolean paid = payment(total);

        if (paid) {
            loyaltyReward(total);
            eBill(customer, mobile, total);
            feedback();
            salesReport();

            System.out.println("\n==============================================");
            System.out.println("      Thank You for visiting Kadasi Bench!");
            System.out.println("              Visit Again!");
            System.out.println("==============================================");
        }
    }
}
