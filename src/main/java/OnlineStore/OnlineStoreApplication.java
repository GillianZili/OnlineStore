package OnlineStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the Online Store Spring Boot application.
 *
 * This class bootstraps the entire application using {@link SpringApplication#run(Class, String[])}.
 * It enables component scanning, auto configuration, and configuration properties support
 * through the {@code @SpringBootApplication} annotation.
 */
@SpringBootApplication
public class OnlineStoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(OnlineStoreApplication.class, args);
  }
}