import java.util.ArrayList;

public class Order {

    private ArrayList<OrderItem> orderItems;

    public Order() {
        orderItems = new ArrayList<>();
    }

    // Add food to order
    public void addItem(MenuItem menuItem, int quantity) {

        if (menuItem == null) {
            System.out.println("Food item cannot be null!");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0!");
            return;
        }

        for (OrderItem item : orderItems) {

            if (item.getMenuItem().getFoodId() == menuItem.getFoodId()) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("Quantity updated!");
                return;
            }
        }

        orderItems.add(new OrderItem(menuItem, quantity));
        System.out.println("Food added to order!");
    }

    // Remove food from order
    public void removeItem(int foodId) {

        for (int i = 0; i < orderItems.size(); i++) {

            if (orderItems.get(i).getMenuItem().getFoodId() == foodId) {
                orderItems.remove(i);
                System.out.println("Food removed from order!");
                return;
            }
        }

        System.out.println("Food not found in order!");
    }

    // Modify quantity
    public void modifyQuantity(int foodId, int newQuantity) {

        for (int i = 0; i < orderItems.size(); i++) {

            if (orderItems.get(i).getMenuItem().getFoodId() == foodId) {

                if (newQuantity <= 0) {
                    orderItems.remove(i);
                    System.out.println("Food removed from order!");
                } else {
                    orderItems.get(i).setQuantity(newQuantity);
                    System.out.println("Quantity updated!");
                }

                return;
            }
        }

        System.out.println("Food not found in order!");
    }

    // View current order
    public void viewOrder() {

        System.out.println("\n----- CURRENT ORDER -----");

        if (orderItems.isEmpty()) {
            System.out.println("Order is empty!");
            return;
        }

        for (OrderItem item : orderItems) {
            item.displayOrderItem();
        }

        System.out.println("-------------------------");
        System.out.println("Total: ₹" + getTotal());
    }

    // Calculate total
    public double getTotal() {

        double total = 0;

        for (OrderItem item : orderItems) {
            total += item.getTotalPrice();
        }

        return total;
    }

    // Check whether order is empty
    public boolean isEmpty() {
        return orderItems.isEmpty();
    }
}
