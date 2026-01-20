package my.study.opencode.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordBuilderExample {

  private static final Logger logger = LoggerFactory.getLogger(RecordBuilderExample.class);

  /**
   * A Record with many parameters. Java Records do not automatically generate a Builder, but you
   * can implement one easily.
   */
  public record ComplexConfig(
      String host,
      int port,
      String database,
      String username,
      String password,
      boolean sslEnabled,
      int timeoutMs,
      int maxConnections) {

    // Static factory method to start the builder
    public static Builder builder() {
      return new Builder();
    }

    // Static Builder Class
    public static class Builder {
      // Default values can be set here
      private String host = "localhost";
      private int port = 8080;
      private String database;
      private String username;
      private String password;
      private boolean sslEnabled = false;
      private int timeoutMs = 5000;
      private int maxConnections = 10;

      public Builder host(String host) {
        this.host = host;
        return this;
      }

      public Builder port(int port) {
        this.port = port;
        return this;
      }

      public Builder database(String database) {
        this.database = database;
        return this;
      }

      public Builder username(String username) {
        this.username = username;
        return this;
      }

      public Builder password(String password) {
        this.password = password;
        return this;
      }

      public Builder sslEnabled(boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
        return this;
      }

      public Builder timeoutMs(int timeoutMs) {
        this.timeoutMs = timeoutMs;
        return this;
      }

      public Builder maxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
        return this;
      }

      public ComplexConfig build() {
        // Validation can also happen here before creating the record
        if (database == null) {
          throw new IllegalStateException("Database name is required");
        }
        return new ComplexConfig(
            host, port, database, username, password, sslEnabled, timeoutMs, maxConnections);
      }
    }
  }

  public static void main(String[] args) {
    // Using the Builder to create a Record instance
    ComplexConfig config =
        ComplexConfig.builder()
            .host("192.168.1.100")
            .database("production_db")
            .username("admin")
            .sslEnabled(true)
            .maxConnections(50)
            .build();

    logger.info("Config created: {}", config);
  }
}
