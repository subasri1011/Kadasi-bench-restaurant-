public class TestMenuItem {

    public static void main(String[] args) {

        // Create menu manager
        MenuManager menuManager = new MenuManager();

        // Add food to menu
        menuManager.addFood(new MenuItem(101, "French Fries", 120));
        menuManager.addFood(new MenuItem(102, "Chicken 65", 180));
        menuManager.addFood(new MenuItem(201, "Chicken Biryani", 250));
        menuManager.addFood(new MenuItem(202, "Veg Fried Rice", 160));
        menuManager.addFood(new MenuItem(301, "Coffee", 70));

        // Display menu
        menuManager.displayMenu();

        // Create order manager
        OrderManager orderManager = new OrderManager();

        // Add food to order
        System.out.println("\n--- ADDING FOOD TO ORDER ---");

        orderManager.addFood(menuManager, 201, 2);
        orderManager.addFood(menuManager, 101, 1);
        orderManager.addFood(menuManager, 301, 2);

        // View order
        orderManager.viewOrder();

        // Modify quantity
        System.out.println("\n--- MODIFY QUANTITY ---");

        orderManager.modifyQuantity(101, 3);

        orderManager.viewOrder();

        // Remove food
        System.out.println("\n--- REMOVE FOOD ---");

        orderManager.removeFood(301);

        orderManager.viewOrder();

        // Confirm order
        System.out.println("\n--- CONFIRMING ORDER ---");

        orderManager.confirmOrder();
    }
}
