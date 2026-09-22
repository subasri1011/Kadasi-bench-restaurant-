public class MenuItem {

    private int foodId;
    private String foodName;
    private double price;

    public MenuItem(int foodId, String foodName, double price) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void displayItem() {
        System.out.println(foodId + " | " + foodName + " | ₹" + price);
    }
}
