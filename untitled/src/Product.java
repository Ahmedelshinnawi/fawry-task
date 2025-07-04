import java.time.LocalDate;

interface Shippable {
    String getName();
    double getWeight();
}


abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }


    public void reduceQuantity(int quan){
       if (quan > quantity) throw new RuntimeException("Not enough quantity");
       quantity -= quan;
    }

    public boolean isExpired() {
        return false;
    }

}

