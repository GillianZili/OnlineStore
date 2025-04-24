package OnlineStore.parseJSON;

import static org.junit.jupiter.api.Assertions.*;

import OnlineStore.model.Item;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for testing whether the json files can be parsed correctly or not
 */
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
        "Life Brand Beverages Cedar Rapids Iowa Soda Bottle ACL Graphics 26 Oz."+" ",
        items.get(0).getName()
    );
  }
}