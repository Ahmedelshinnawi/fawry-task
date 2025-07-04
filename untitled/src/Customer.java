import java.util.*;

class Customer {
    private String name;
    private double balance;
    private List<CartItem> cart = new ArrayList<>();

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void addToCart(Product product, int quantity) {
        if (quantity <= 0) {
            throw new RuntimeException("Quantity must be positive.");
        }
        if (quantity > product.getQuantity()) {
            throw new RuntimeException("Requested quantity (" + quantity + ") exceeds available stock (" + product.getQuantity() + ") for " + product.getName());
        }
        cart.add(new CartItem(product, quantity));
        System.out.println("Added " + quantity + " x " + product.getName() + " to cart.");
    }

    public List<CartItem> getCart() {
        return new ArrayList<>(cart);
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        
        System.out.println("\n=== Cart Contents ===");
        for (CartItem item : cart) {
            System.out.printf("%s - Quantity: %d, Price: $%.2f each, Total: $%.2f\n", 
                item.getProduct().getName(), 
                item.getQuantity(), 
                item.getProduct().getPrice(), 
                item.getTotalPrice());
        }
        System.out.println("=====================\n");
    }

    public void clearCart() {
        cart.clear();
        System.out.println("Cart cleared.");
    }

    public void checkout() {
        if (cart.isEmpty()) {
            throw new RuntimeException("Cart is empty.");
        }

        // Validate items before processing
        for (CartItem item : cart) {
            if (item.isExpired()) {
                throw new RuntimeException(item.getProduct().getName() + " is expired.");
            }
            if (item.getQuantity() > item.getProduct().getQuantity()) {
                throw new RuntimeException(item.getProduct().getName() + " is out of stock. Available: " + 
                    item.getProduct().getQuantity() + ", Requested: " + item.getQuantity());
            }
        }

        double subtotal = 0;
        List<Shippable> toShip = new ArrayList<>();

        for (CartItem item : cart) {
            subtotal += item.getTotalPrice();
            if (item.isShippable()) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    toShip.add((Shippable) item.getProduct());
                }
            }
        }

        double shippingFee = 0;
        for (Shippable item : toShip) {
            shippingFee += item.getWeight() * 5;
        }

        double total = subtotal + shippingFee;
        
        if (balance < total) {
            throw new RuntimeException(String.format("Insufficient balance. Required: $%.2f, Available: $%.2f", total, balance));
        }

        // Reduce quantities from inventory
        for (CartItem item : cart) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        balance -= total;

        System.out.println("\n=== Checkout Summary ===");
        System.out.println("Customer: " + name);
        System.out.printf("Order Subtotal: $%.2f\n", subtotal);
        System.out.printf("Shipping Fees: $%.2f\n", shippingFee);
        System.out.printf("Total Paid: $%.2f\n", total);
        System.out.printf("Customer Balance After Payment: $%.2f\n", balance);
        System.out.println("========================");

        if (!toShip.isEmpty()) {
            ShippingService shipService = new ShippingService();
            shipService.shipItems(toShip);
        }

        cart.clear();
        System.out.println("Checkout completed successfully!\n");
    }
}
