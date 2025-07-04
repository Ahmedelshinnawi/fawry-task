class CartItem {
    Product product;
    int quantity;

    public CartItem(Product product, int quantity) {
        if (quantity > product.getQuantity()) throw new RuntimeException("Quantity exceeds stock.");
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public boolean isExpired() {
        return product.isExpired();
    }

    public boolean isShippable() {
        return product instanceof Shippable;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
