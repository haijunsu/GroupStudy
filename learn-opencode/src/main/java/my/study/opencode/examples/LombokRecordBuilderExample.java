package my.study.opencode.examples;

import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LombokRecordBuilderExample {

  private static final Logger logger = LoggerFactory.getLogger(LombokRecordBuilderExample.class);

  @Builder
  public record ServerConfig(String host, int port, String region, boolean active) {}

  public static void main(String[] args) {
    ServerConfig config =
        ServerConfig.builder()
            .host("api.example.com")
            .port(443)
            .region("us-west-1")
            .active(true)
            .build();

    logger.info("Config created via Lombok Builder: {}", config);
  }
}
