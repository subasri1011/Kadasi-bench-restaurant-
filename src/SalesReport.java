public class SalesReport {

    private int totalOrders;
    private double totalSales;

    public SalesReport() {
        totalOrders = 0;
        totalSales = 0.0;
    }

    public void addSale(double amount) {
        totalOrders++;
        totalSales += amount;
    }

    public void displayReport() {

        System.out.println();
        System.out.println("================================");
        System.out.println("          DAILY SALES");
        System.out.println("================================");
        System.out.println("Total Orders : " + totalOrders);
        System.out.println("Total Sales  : Rs." + totalSales);
        System.out.println("================================");
    }
}
