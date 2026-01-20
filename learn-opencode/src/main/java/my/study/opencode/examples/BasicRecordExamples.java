package my.study.opencode.examples;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicRecordExamples {

  private static final Logger logger = LoggerFactory.getLogger(BasicRecordExamples.class);

  // Record with compact constructor for validation
  public record User(String username, String email) {
    public User {
      Objects.requireNonNull(username, "Username cannot be null");
      Objects.requireNonNull(email, "Email cannot be null");
      if (!email.contains("@")) {
        throw new IllegalArgumentException("Invalid email format");
      }
    }
  }

  // Record with a static factory method
  public record Point(int x, int y) {
    public static Point origin() {
      return new Point(0, 0);
    }
  }

  // Record with additional method
  public record Rectangle(double length, double width) {
    public double area() {
      return length * width;
    }
  }

  public static void main(String[] args) {
    try {
      User user = new User("john_doe", "john@example.com");
      logger.info("User created: {}", user);

      User invalidUser = new User("jane_doe", "invalid-email");
    } catch (IllegalArgumentException e) {
      logger.error("Validation failed: {}", e.getMessage());
    }

    Point origin = Point.origin();
    logger.info("Origin: {}", origin);

    Rectangle rect = new Rectangle(5, 10);
    logger.info("Rectangle Area: {}", rect.area());
  }
}
