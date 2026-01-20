package my.study.opencode.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvancedRecordExamples {

  private static final Logger logger = LoggerFactory.getLogger(AdvancedRecordExamples.class);

  public interface Shape {
    double area();

    double perimeter();
  }

  public record Circle(double radius) implements Shape {
    @Override
    public double area() {
      return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
      return 2 * Math.PI * radius;
    }
  }

  public record Rectangle(double length, double width) implements Shape {
    @Override
    public double area() {
      return length * width;
    }

    @Override
    public double perimeter() {
      return 2 * (length + width);
    }
  }

  public static void printShapeInfo(Shape shape) {
    logger.info("Shape Type: {}", shape.getClass().getSimpleName());
    logger.info("Area: {}", shape.area());
    logger.info("Perimeter: {}", shape.perimeter());
  }

  public static void main(String[] args) {
    Shape circle = new Circle(5.0);
    Shape rectangle = new Rectangle(4.0, 6.0);

    printShapeInfo(circle);
    printShapeInfo(rectangle);
  }
}
