import java.util.List;

public class ShippingService {
    
    public void shipItems(List<Shippable> shippableItems) {
        if (shippableItems.isEmpty()) {
            System.out.println("No items to ship.");
            return;
        }
        
        System.out.println("\n=== Shipping Service ===");
        System.out.println("Processing shipment for the following items:");
        
        double totalWeight = 0;
        for (Shippable item : shippableItems) {
            System.out.printf("- %s (Weight: %.2f kg)\n", item.getName(), item.getWeight());
            totalWeight += item.getWeight();
        }
        
        System.out.printf("Total shipment weight: %.2f kg\n", totalWeight);
        System.out.println("Items have been sent for shipping.");
        System.out.println("========================\n");
    }
} 