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
  CommandLineRunner initDatabase(ItemRepository emp_repo, UsersRepository user_repo) {

    return args -> {
      log.info("Preloading " + emp_repo.save(new Item(1001L, "laptop",999.00,5)));
      log.info("Preloading " + emp_repo.save(new Item(1002L, "smartphone",800.50,8)));
      log.info("Preloading " + user_repo.save(new Users(01L,"Ziliang")));
      log.info("Preloading " + user_repo.save(new Users(02L, "Gillian")));
    };
  }
}
