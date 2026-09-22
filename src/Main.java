import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Table> tables = new ArrayList<>();
    static ArrayList<MenuItem> menu = new ArrayList<>();
    static ArrayList<Order> orders = new ArrayList<>();

    static Queue<String> waitingQueue = new LinkedList<>();

    static double totalSales = 0;
    static int nextOrderId = 1001;

    public static void main(String[] args) {

        setupTables();
        setupMenu();

        while (true) {

            System.out.println("\n======================================");
            System.out.println("    SMART RESTAURANT MANAGEMENT");
            System.out.println("======================================");
            System.out.println("1. Dine-In Customer");
            System.out.println("2. Takeaway Customer");
            System.out.println("3. Admin");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    dineIn();
                    break;

                case 2:
                    takeaway();
                    break;

                case 3:
                    adminLogin();
                    break;

                case 4:
                    System.out.println("Thank you! Visit Again!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void setupTables() {
        tables.add(new Table(1, 2));
        tables.add(new Table(2, 2));
        tables.add(new Table(3, 4));
        tables.add(new Table(4, 4));
        tables.add(new Table(5, 6));
        tables.add(new Table(6, 8));
    }

    static void setupMenu() {
        menu.add(new MenuItem(101, "French Fries", 120, 10));
        menu.add(new MenuItem(102, "Chicken 65", 180, 10));
        menu.add(new MenuItem(201, "Chicken Biryani", 250, 10));
        menu.add(new MenuItem(202, "Veg Fried Rice", 160, 10));
        menu.add(new MenuItem(301, "Fresh Lime", 80, 10));
        menu.add(new MenuItem(302, "Coffee", 70, 10));
        menu.add(new MenuItem(401, "Ice Cream", 100, 10));
    }

    static void dineIn() {

        System.out.print("\nCustomer Name: ");
        String name = sc.nextLine();

        System.out.print("Phone Number: ");
        String phone = sc.nextLine();

        System.out.print("Number of Guests: ");
        int guests = sc.nextInt();
        sc.nextLine();

        Table table = findBestTable(guests);

        if (table == null) {

            System.out.println("\nNo suitable table available.");

            System.out.print("Join waiting queue? (y/n): ");
            String answer = sc.nextLine();

            if (answer.equalsIgnoreCase("y")) {
                waitingQueue.add(name + " - " + guests + " Guests");

                System.out.println("Added to waiting queue.");
                System.out.println(
                    "Estimated Waiting Time: "
                    + waitingQueue.size() * 10
                    + " minutes"
                );
            }

            return;
        }

        table.occupied = true;

        System.out.println("\nSmart Table Allocation Successful!");
        System.out.println(
            "Table " + table.number
            + " allocated | Capacity: "
            + table.capacity
        );

        placeOrder(name, phone, table, false);
    }

    static void takeaway() {

        System.out.print("\nCustomer Name: ");
        String name = sc.nextLine();

        System.out.print("Phone Number: ");
        String phone = sc.nextLine();

        placeOrder(name, phone, null, true);
    }

    static Table findBestTable(int guests) {

        Table best = null;

        for (Table t : tables) {

            if (!t.occupied && t.capacity >= guests) {

                if (best == null || t.capacity < best.capacity) {
                    best = t;
                }
            }
        }

        return best;
    }

    static void placeOrder(
            String name,
            String phone,
            Table table,
            boolean takeaway) {

        Order order = new Order();

        order.orderId = nextOrderId++;
        order.customerName = name;
        order.phone = phone;
        order.table = table;
        order.takeaway = takeaway;
        order.status = "PLACED";

        while (true) {

            showMenu();

            System.out.print("\nEnter Item ID (0 to finish): ");
            int id = sc.nextInt();

            if (id == 0) {
                break;
            }

            MenuItem item = findMenuItem(id);

            if (item == null) {
                System.out.println("Invalid Item ID!");
                continue;
            }

            if (item.stock <= 0) {
                System.out.println("Item is SOLD OUT!");
                continue;
            }

            System.out.print("Quantity: ");
            int qty = sc.nextInt();

            if (qty <= 0 || qty > item.stock) {
                System.out.println("Invalid quantity!");
                continue;
            }

            order.items.add(new OrderItem(item, qty));
            item.stock -= qty;

            System.out.println(
                item.name + " x " + qty + " added."
            );

            if (item.stock <= 3) {
                System.out.println(
                    "LOW STOCK ALERT: "
                    + item.name
                    + " remaining = "
                    + item.stock
                );
            }

            if (id == 201 || id == 202) {
                System.out.println(
                    "Recommendation: Try Fresh Lime or Ice Cream!"
                );
            }
        }

        if (order.items.isEmpty()) {

            System.out.println("No items ordered.");

            if (table != null) {
                table.occupied = false;
            }

            return;
        }

        orders.add(order);
        generateBill(order);
    }

    static void showMenu() {

        System.out.println("\n=============== MENU ===============");
        System.out.printf(
            "%-5s %-20s %-8s %-7s%n",
            "ID",
            "Item",
            "Price",
            "Stock"
        );

        for (MenuItem item : menu) {
            System.out.printf(
                "%-5d %-20s %-8.2f %-7d%n",
                item.id,
                item.name,
                item.price,
                item.stock
            );
        }
    }

    static MenuItem findMenuItem(int id) {

        for (MenuItem item : menu) {
            if (item.id == id) {
                return item;
            }
        }

        return null;
    }

    static void generateBill(Order order) {

        double subtotal = 0;

        System.out.println("\n=============== BILL ===============");

        for (OrderItem oi : order.items) {

            double amount = oi.item.price * oi.quantity;
            subtotal += amount;

            System.out.printf(
                "%-20s %d x %.2f = %.2f%n",
                oi.item.name,
                oi.quantity,
                oi.item.price,
                amount
            );
        }

        sc.nextLine();

        System.out.print("\nCoupon Code (SAVE10 / NONE): ");
        String coupon = sc.nextLine();

        double discount = 0;

        if (
            coupon.equalsIgnoreCase("SAVE10")
            && subtotal >= 500
        ) {
            discount = subtotal * 0.10;
        }

        double taxable = subtotal - discount;
        double gst = taxable * 0.05;
        double total = taxable + gst;

        System.out.printf("Subtotal : Rs. %.2f%n", subtotal);
        System.out.printf("Discount : Rs. %.2f%n", discount);
        System.out.printf("GST 5%%   : Rs. %.2f%n", gst);
        System.out.printf("TOTAL    : Rs. %.2f%n", total);

        System.out.print("\nSplit bill among how many people? ");
        int people = sc.nextInt();

        if (people <= 0) {
            people = 1;
        }

        System.out.printf(
            "Per Person: Rs. %.2f%n",
            total / people
        );

        System.out.println("\nPayment Mode");
        System.out.println("1. Cash");
        System.out.println("2. UPI");
        System.out.println("3. Card");

        System.out.print("Choose: ");
        int payment = sc.nextInt();

        String paymentMode;

        if (payment == 1) {
            paymentMode = "Cash";
        } else if (payment == 2) {
            paymentMode = "UPI";
        } else {
            paymentMode = "Card";
        }

        order.total = total;
        order.status = "PAID";
        order.paymentMode = paymentMode;

        totalSales += total;

        System.out.println("\nPayment Successful!");
        System.out.println("Mode: " + paymentMode);
        System.out.println("Order ID: " + order.orderId);

        System.out.print("Rating (1-5): ");
        int rating = sc.nextInt();

        sc.nextLine();

        System.out.print("Feedback: ");
        order.feedback = sc.nextLine();
        order.rating = rating;

        if (order.table != null) {
            order.table.occupied = false;

            System.out.println(
                "Table "
                + order.table.number
                + " released."
            );
        }

        System.out.println("Thank you " + order.customerName + "!");
    }

    static void adminLogin() {

        System.out.print("\nUsername: ");
        String user = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        if (!user.equals("admin") || !pass.equals("1234")) {
            System.out.println("Invalid login!");
            return;
        }

        adminMenu();
    }

    static void adminMenu() {

        while (true) {

            System.out.println("\n========== ADMIN MENU ==========");
            System.out.println("1. View Tables");
            System.out.println("2. View Menu / Stock");
            System.out.println("3. Restock Item");
            System.out.println("4. View Orders");
            System.out.println("5. Update Order Status");
            System.out.println("6. Waiting Queue");
            System.out.println("7. Sales Analytics");
            System.out.println("8. Feedback Report");
            System.out.println("9. Search Order");
            System.out.println("10. Back");

            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewTables();
                    break;
                case 2:
                    showMenu();
                    break;
                case 3:
                    restock();
                    break;
                case 4:
                    viewOrders();
                    break;
                case 5:
                    updateStatus();
                    break;
                case 6:
                    viewWaitingQueue();
                    break;
                case 7:
                    salesAnalytics();
                    break;
                case 8:
                    feedbackReport();
                    break;
                case 9:
                    searchOrder();
                    break;
                case 10:
                    sc.nextLine();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void viewTables() {

        System.out.println("\n========== TABLE STATUS ==========");

        for (Table t : tables) {
            System.out.println(
                "Table " + t.number
                + " | Seats: " + t.capacity
                + " | "
                + (t.occupied ? "OCCUPIED" : "AVAILABLE")
            );
        }
    }

    static void restock() {

        System.out.print("Item ID: ");
        int id = sc.nextInt();

        MenuItem item = findMenuItem(id);

        if (item == null) {
            System.out.println("Invalid item!");
            return;
        }

        System.out.print("Add Quantity: ");
        int qty = sc.nextInt();

        item.stock += qty;

        System.out.println("Stock Updated!");
    }

    static void viewOrders() {

        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        for (Order o : orders) {
            System.out.println(
                "Order #" + o.orderId
                + " | " + o.customerName
                + " | " + o.status
                + " | Rs." + o.total
            );
        }
    }

    static void updateStatus() {

        System.out.print("Order ID: ");
        int id = sc.nextInt();

        Order order = findOrder(id);

        if (order == null) {
            System.out.println("Order not found!");
            return;
        }

        System.out.println("1. PLACED");
        System.out.println("2. PREPARING");
        System.out.println("3. READY");
        System.out.println("4. SERVED");

        System.out.print("Choose: ");
        int status = sc.nextInt();

        switch (status) {
            case 1:
                order.status = "PLACED";
                break;
            case 2:
                order.status = "PREPARING";
                break;
            case 3:
                order.status = "READY";
                break;
            case 4:
                order.status = "SERVED";
                break;
            default:
                System.out.println("Invalid status!");
                return;
        }

        System.out.println("Order Status Updated: " + order.status);
    }

    static void viewWaitingQueue() {

        if (waitingQueue.isEmpty()) {
            System.out.println("Waiting queue empty.");
            return;
        }

        System.out.println("\n========== WAITING QUEUE ==========");

        int position = 1;

        for (String customer : waitingQueue) {
            System.out.println(position + ". " + customer);
            position++;
        }
    }

    static void salesAnalytics() {

        System.out.println("\n========== SALES ANALYTICS ==========");

        System.out.println("Total Orders: " + orders.size());

        System.out.printf(
            "Total Sales : Rs. %.2f%n",
            totalSales
        );

        if (!orders.isEmpty()) {
            System.out.printf(
                "Average Bill: Rs. %.2f%n",
                totalSales / orders.size()
            );
        }

        System.out.println(
            "Popular Item: " + findPopularItem()
        );
    }

    static String findPopularItem() {

        HashMap<String, Integer> count = new HashMap<>();

        for (Order o : orders) {
            for (OrderItem oi : o.items) {
                count.put(
                    oi.item.name,
                    count.getOrDefault(oi.item.name, 0)
                    + oi.quantity
                );
            }
        }

        String best = "N/A";
        int max = 0;

        for (String item : count.keySet()) {
            if (count.get(item) > max) {
                max = count.get(item);
                best = item;
            }
        }

        return best;
    }

    static void feedbackReport() {

        int count = 0;
        int totalRating = 0;

        for (Order o : orders) {
            if (o.rating > 0) {
                count++;
                totalRating += o.rating;

                System.out.println(
                    o.customerName
                    + " -> "
                    + o.rating
                    + "/5 : "
                    + o.feedback
                );
            }
        }

        if (count == 0) {
            System.out.println("No feedback available.");
        } else {
            System.out.printf(
                "Average Rating: %.2f / 5%n",
                (double) totalRating / count
            );
        }
    }

    static void searchOrder() {

        System.out.print("Order ID: ");
        int id = sc.nextInt();

        Order order = findOrder(id);

        if (order == null) {
            System.out.println("Order not found!");
            return;
        }

        System.out.println("\nOrder #" + order.orderId);
        System.out.println("Customer: " + order.customerName);
        System.out.println("Status: " + order.status);
        System.out.println("Payment: " + order.paymentMode);
        System.out.println("Total: Rs." + order.total);
    }

    static Order findOrder(int id) {
        for (Order o : orders) {
            if (o.orderId == id) {
                return o;
            }
        }
        return null;
    }

    static class Table {
        int number;
        int capacity;
        boolean occupied;

        Table(int number, int capacity) {
            this.number = number;
            this.capacity = capacity;
            this.occupied = false;
        }
    }

    static class MenuItem {
        int id;
        String name;
        double price;
        int stock;

        MenuItem(int id, String name, double price, int stock) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    }

    static class OrderItem {
        MenuItem item;
        int quantity;

        OrderItem(MenuItem item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }
    }

    static class Order {
        int orderId;
        String customerName;
        String phone;
        String status;
        String paymentMode;
        String feedback = "";

        boolean takeaway;
        int rating = 0;
        double total = 0;

        Table table;

        ArrayList<OrderItem> items = new ArrayList<>();
    }
}
