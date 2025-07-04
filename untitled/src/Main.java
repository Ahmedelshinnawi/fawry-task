
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
   
        System.out.println("=== E-Commerce System Demonstration ===\n");
        
       
        Product cheese = new ExpirableShippableProduct("Cheese", 5.99, 10, LocalDate.now().plusDays(7), 0.5);
        Product biscuits = new ExpirableProduct("Biscuits", 3.50, 15, LocalDate.now().plusDays(30));
        Product tv = new ShippableProduct("Smart TV", 599.99, 5, 25.0);
        Product mobile = new ShippableProduct("Mobile Phone", 299.99, 8, 0.2);
        Product scratchCard = new NonExpirableNonShippableProduct("Mobile Scratch Card", 10.00, 100);
        Product expiredProduct = new ExpirableProduct("Expired Milk", 2.99, 5, LocalDate.now().minusDays(1));
        
        
        Customer ahmed = new Customer("Ahmed", 1000.0);
        Customer mohamed = new Customer("Mohamed", 50.0);  
        Customer ali = new Customer("Ali", 5000.0); 
        
        
        System.out.println("*** Test Case 1: Normal Successful Checkout ***");
        try {
            ahmed.addToCart(cheese, 2);
            ahmed.addToCart(tv, 1);
            ahmed.addToCart(scratchCard, 3);
            ahmed.viewCart();
            ahmed.checkout();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("*** Test Case 2: Empty Cart Checkout ***");
        try {
            mohamed.checkout();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
        
        System.out.println("*** Test Case 3: Insufficient Balance ***");
        try {
            mohamed.addToCart(tv, 1);
            mohamed.checkout();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
        
        System.out.println("*** Test Case 4: Expired Product ***");
        try {
            ali.addToCart(expiredProduct, 1);
            ali.checkout();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            ali.clearCart(); 
            System.out.println();
        }
        
        System.out.println("*** Test Case 5: Out of Stock ***");
        try {
            System.out.println("Current TV stock: " + tv.getQuantity());
            ali.addToCart(tv, tv.getQuantity()); 
            ali.checkout();
            
            ali.addToCart(tv, 1);
            ali.checkout();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
        
        System.out.println("*** Test Case 6: Quantity Exceeds Stock During Add to Cart ***");
        try {
            ali.addToCart(mobile, 20); 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
        
        System.out.println("*** Test Case 7: Mixed Cart - Shippable and Non-Shippable ***");
        try {
            Customer mahmoud = new Customer("Mahmoud", 800.0);
            mahmoud.addToCart(mobile, 2);
            mahmoud.addToCart(biscuits, 5);
            mahmoud.addToCart(scratchCard, 10);
            mahmoud.viewCart();
            mahmoud.checkout();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("*** Test Case 8: Invalid Quantity ***");
        try {
            ali.addToCart(cheese, 0);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            ali.addToCart(cheese, -2);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
        
        System.out.println("*** Test Case 9: Product Inventory Tracking ***");
        System.out.println("Initial mobile stock: " + mobile.getQuantity());
        
        Customer amany = new Customer("Amany", 1000.0);
        amany.addToCart(mobile, 3);
        amany.checkout();
        
        System.out.println("Mobile stock after Amany's purchase: " + mobile.getQuantity());
        
        System.out.println("*** Test Case 10: Large Order with Multiple Shippable Items ***");
        try {
            Product heavyItem = new ShippableProduct("Refrigerator", 899.99, 3, 50.0);
            Customer samira = new Customer("Samira", 3000.0);
            samira.addToCart(heavyItem, 2);
            samira.addToCart(cheese, 5);
            samira.checkout();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("=== All test cases completed! ===");
    }
}