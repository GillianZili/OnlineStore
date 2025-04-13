package OnlineStore;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


public class ItemLoaderServiceTest {

  @Test
  void testLoadItemsFromJson() throws IOException {
    ItemLoaderService service = new ItemLoaderService();

    String file = "D:\\work_palace\\OnlineStore\\ebay_data\\ebay_items_beverages_200.json";
    service.loadItemsFromJson(file);

    List<Item> items = service.getItemList();

    System.out.println("Loaded " + items.size() + " items.");
    assertEquals(200, items.size());

    assertEquals(
        "GREENWOOD SC SEAGO BEVERAGES  SODA BOTTLE ACL & EMBOSSED SCARCE",
        items.get(0).getName()
    );
  }
}