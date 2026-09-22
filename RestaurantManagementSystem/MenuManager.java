import java.util.ArrayList;

public class MenuManager {

    private ArrayList<MenuItem> menuItems;

    public MenuManager() {
        menuItems = new ArrayList<>();
    }

    // Add food
    public void addFood(MenuItem item) {
        menuItems.add(item);
        System.out.println("Food added successfully!");
    }

    // Remove food
    public void removeFood(int foodId) {

        for (int i = 0; i < menuItems.size(); i++) {

            if (menuItems.get(i).getFoodId() == foodId) {
                menuItems.remove(i);
                System.out.println("Food removed successfully!");
                return;
            }
        }

        System.out.println("Food not found!");
    }

    // Update price
    public void updatePrice(int foodId, double newPrice) {

        for (MenuItem item : menuItems) {

            if (item.getFoodId() == foodId) {
                item.setPrice(newPrice);
                System.out.println("Price updated successfully!");
                return;
            }
        }

        System.out.println("Food not found!");
    }

    // Display menu
    public void displayMenu() {

        System.out.println("\n----- MENU -----");

        for (MenuItem item : menuItems) {
            item.displayItem();
        }
    }

    // Search food
    public void searchFood(String name) {

        boolean found = false;

        for (MenuItem item : menuItems) {

            if (item.getFoodName().toLowerCase().contains(name.toLowerCase())) {
                item.displayItem();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Food not found!");
        }
    }

    // Find food by ID
    public MenuItem findFood(int foodId) {

        for (MenuItem item : menuItems) {

            if (item.getFoodId() == foodId) {
                return item;
            }
        }

        return null;
    }
}
