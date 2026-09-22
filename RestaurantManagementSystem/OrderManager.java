public class OrderManager {

    private Order currentOrder;

    public OrderManager() {
        currentOrder = new Order();
    }

    // Add food to current order
    public void addFood(MenuManager menuManager, int foodId, int quantity) {

        if (menuManager == null) {
            System.out.println("Menu manager is not available!");
            return;
        }

        MenuItem item = menuManager.findFood(foodId);

        if (item == null) {
            System.out.println("Food not found in menu!");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0!");
            return;
        }

        currentOrder.addItem(item, quantity);
    }

    // Remove food from order
    public void removeFood(int foodId) {
        currentOrder.removeItem(foodId);
    }

    // Modify quantity
    public void modifyQuantity(int foodId, int quantity) {
        currentOrder.modifyQuantity(foodId, quantity);
    }

    // View current order
    public void viewOrder() {
        currentOrder.viewOrder();
    }

    // Confirm order
    public void confirmOrder() {

        if (currentOrder.isEmpty()) {
            System.out.println("Cannot confirm an empty order!");
            return;
        }

        System.out.println("\n===== ORDER CONFIRMED =====");
        currentOrder.viewOrder();
        System.out.println("Thank you for your order!");
        System.out.println("===========================");
    }
}
