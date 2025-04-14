package OnlineStore;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;


public class ItemLoaderServiceTest {

  @Test
  void testLoadItemsFromJson() throws IOException {
    ItemJsonParseService service = new ItemJsonParseService();

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