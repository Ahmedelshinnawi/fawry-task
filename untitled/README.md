# E-Commerce System

A comprehensive Java-based e-commerce system that handles product management, shopping cart functionality, and checkout processing with support for expirable and shippable products.

## Features

### Product Management

- **Abstract Product Class**: Base class for all products with name, price, and quantity
- **Expirable Products**: Products with expiry dates (e.g., Cheese, Biscuits, Milk)
- **Shippable Products**: Products that require shipping with weight information (e.g., TV, Mobile)
- **Combination Products**: Products that are both expirable and shippable (e.g., Cheese)
- **Digital Products**: Non-expirable, non-shippable products (e.g., Mobile Scratch Cards)

### Shopping Cart System

- Add products to cart with quantity validation
- View cart contents with detailed pricing
- Automatic inventory validation
- Support for mixed product types

### Checkout Process

- **Comprehensive Validation**:
  - Empty cart detection
  - Expired product checking
  - Stock availability verification
  - Customer balance validation
- **Pricing Calculation**:
  - Order subtotal calculation
  - Shipping fee calculation ($5 per kg for shippable items)
  - Total amount calculation
- **Inventory Management**: Automatic stock reduction after successful checkout
- **Shipping Integration**: Automatic dispatch to ShippingService for applicable items

## Class Structure

### Core Classes

#### `Product` (Abstract)

Base class for all products containing:

- Name, price, quantity
- Basic inventory management
- Expiry checking (default: false)

#### `ExpirableProduct`

Extends Product for items with expiry dates:

- LocalDate expiry tracking
- Automatic expiry validation

#### `ShippableProduct`

Extends Product and implements Shippable interface:

- Weight information for shipping calculations
- Integration with shipping service

#### `ExpirableShippableProduct`

Combines both expirable and shippable functionality:

- Perfect for products like cheese, fresh produce
- Handles both expiry and shipping requirements

#### `NonExpirableNonShippableProduct`

For digital or service products:

- No expiry concerns
- No shipping requirements

### Support Classes

#### `Customer`

- Balance management
- Cart operations (add, view, clear)
- Checkout processing with comprehensive validation

#### `CartItem`

- Links products with quantities
- Price calculations
- Product type checking

#### `ShippingService`

- Processes shipments for Shippable items
- Weight-based shipping fee calculation
- Shipment tracking and logging

### Interfaces

#### `Shippable`

Interface for products requiring shipping:

```java
public interface Shippable {
    String getName();
    double getWeight();
}
```

## Usage Examples

### Creating Products

```java
// Expirable and shippable product
Product cheese = new ExpirableShippableProduct("Cheese", 5.99, 10,
    LocalDate.now().plusDays(7), 0.5);

// Non-expirable shippable product
Product tv = new ShippableProduct("Smart TV", 599.99, 5, 25.0);

// Expirable non-shippable product
Product biscuits = new ExpirableProduct("Biscuits", 3.50, 15,
    LocalDate.now().plusDays(30));

// Digital product (non-expirable, non-shippable)
Product scratchCard = new NonExpirableNonShippableProduct("Mobile Scratch Card", 10.00, 100);
```

### Customer Operations

```java
Customer customer = new Customer("Alice", 1000.0);

// Add items to cart
customer.addToCart(cheese, 2);
customer.addToCart(tv, 1);

// View cart contents
customer.viewCart();

// Process checkout
customer.checkout();
```

## Error Handling

The system provides comprehensive error handling for:

1. **Empty Cart**: "Cart is empty."
2. **Insufficient Balance**: "Insufficient balance. Required: $X.XX, Available: $Y.YY"
3. **Expired Products**: "Product Name is expired."
4. **Out of Stock**: "Product Name is out of stock. Available: X, Requested: Y"
5. **Invalid Quantity**: "Quantity must be positive."
6. **Stock Exceeded**: "Requested quantity (X) exceeds available stock (Y) for Product Name"

## Shipping Calculation

- Shipping fee: $5.00 per kilogram
- Only applies to products implementing the `Shippable` interface
- Each item in the cart contributes its weight multiplied by quantity
- Total shipping = (Total Weight) × $5.00

## Testing

The `Main.java` class includes comprehensive test cases covering:

1. **Normal Successful Checkout**: Mixed cart with various product types
2. **Empty Cart Handling**: Proper error messaging
3. **Insufficient Balance**: Balance validation
4. **Expired Product Detection**: Date-based validation
5. **Out of Stock Scenarios**: Inventory management
6. **Stock Validation**: Quantity checking during cart addition
7. **Mixed Product Types**: Shippable and non-shippable combinations
8. **Invalid Input Handling**: Zero and negative quantities
9. **Inventory Tracking**: Stock reduction after purchases
10. **Large Orders**: Multiple heavy items with complex shipping

## Running the System

1. Compile all Java files:

```bash
javac *.java
```

2. Run the demonstration:

```bash
java Main
```

## Design Assumptions

1. **Shipping Cost**: $5 per kilogram for all shippable items
2. **Currency**: All prices in USD
3. **Expiry Checking**: Performed at checkout time using current system date
4. **Inventory Management**: Stock is reduced only after successful payment
5. **Balance Precision**: Double precision for monetary calculations
6. **Cart Persistence**: Cart contents persist until checkout or manual clearing
7. **Shipping Service**: External service accepts list of Shippable items

## Extension Points

The system is designed for easy extension:

- **New Product Types**: Extend Product class or implement Shippable interface
- **Payment Methods**: Extend Customer class with different payment options
- **Shipping Providers**: Implement new shipping service classes
- **Discount Systems**: Add discount calculation in checkout process
- **Tax Calculation**: Integrate tax computation based on location
- **Order History**: Add order tracking and history management

## Architecture Benefits

1. **Separation of Concerns**: Each class has a specific responsibility
2. **Polymorphism**: Products can be treated uniformly while maintaining specific behaviors
3. **Interface Segregation**: Shippable interface only for relevant products
4. **Error Handling**: Comprehensive validation at each step
5. **Extensibility**: Easy to add new product types and features
6. **Testability**: Clear separation allows for easy unit testing
