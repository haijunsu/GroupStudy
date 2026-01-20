package my.study.opencode.examples;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingCartExample {

  private static final Logger logger = LoggerFactory.getLogger(ShoppingCartExample.class);

  public record Address(String street, String city, String zipCode) {}

  public record Customer(String name, String email, Address address) {}

  public record CartItem(String productId, String productName, int quantity, double price) {
    public double subtotal() {
      return quantity * price;
    }
  }

  public record ShoppingCart(Customer customer, List<CartItem> items) {
    public double totalAmount() {
      return items.stream().mapToDouble(CartItem::subtotal).sum();
    }
  }

  public static void main(String[] args) {
    Address addr = new Address("123 Main St", "Springfield", "12345");
    Customer cust = new Customer("Alice", "alice@example.com", addr);

    List<CartItem> items =
        List.of(
            new CartItem("P001", "Laptop", 1, 1200.00), new CartItem("P002", "Mouse", 2, 25.00));

    ShoppingCart cart = new ShoppingCart(cust, items);

    logger.info("Customer: {}", cart.customer().name());
    logger.info("Total Amount: ${}", cart.totalAmount());
  }
}
