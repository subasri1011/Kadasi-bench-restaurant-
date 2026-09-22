public class Restaurant {

    private String restaurantName;

    public Restaurant(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void displayRestaurantName() {

        System.out.println("========================================");
        System.out.println("        " + restaurantName);
        System.out.println("       RESTAURANT MANAGEMENT SYSTEM");
        System.out.println("========================================");
    }
}
