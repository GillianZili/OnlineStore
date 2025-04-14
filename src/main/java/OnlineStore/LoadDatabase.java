package OnlineStore;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ItemRepository emp_repo) {

    List<String> jsonFiles= List.of(
        "D:\\work_palace\\OnlineStore\\ebay_data\\ebay_items_beverages_200.json",
        "D:\\work_palace\\OnlineStore\\ebay_data\\ebay_items_canned food_200.json",
        "D:\\work_palace\\OnlineStore\\ebay_data\\ebay_items_cleaning supplies_200.json",
        "D:\\work_palace\\OnlineStore\\ebay_data\\ebay_items_dry goods_200.json",
        "D:\\work_palace\\OnlineStore\\ebay_data\\ebay_items_personal care_200.json",
        "D:\\work_palace\\OnlineStore\\ebay_data\\ebay_items_snacks_200.json"
    );

    return args -> {
      for(String fileUrl: jsonFiles) {
        ItemJsonParseService itemService = new ItemJsonParseService();
        itemService.loadItemsFromJson(fileUrl);
        List<Item> items = itemService.getItemList();
        for(Item item:items){
          try{
            log.info("Preloading " + emp_repo.save(item));
          }catch(Exception e){
            System.out.println(item.getName());
            break;
          }
        }
      }
    };
  }
}

