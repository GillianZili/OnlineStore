package OnlineStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(CartRepository emp_repository) {

    return args -> {
      log.info("Preloading " + emp_repository.save(new CartItem(1001L, "laptop",999.00,5)));
      log.info("Preloading " + emp_repository.save(new CartItem(1002L, "smartphone",800.50,8)));
    };
  }
}
