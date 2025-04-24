package OnlineStore.service;

import OnlineStore.model.Item;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parse the response of calling ebay's API.
 */
public class ItemJsonParseService {

  private List<Item> itemList = new ArrayList<>();

  /**
   * Loads items from a JSON file located at the given file path.
   * This method reads the JSON file, parses the "itemSummaries" array,
   * and converts each entry into an {@link Item} object, which is then added to the internal item list.
   * A random storage quantity between 1 and 30 is assigned to each item.
   *
   * @param path the file system path to the JSON file containing item data
   * @throws IOException if the file cannot be read or the JSON is invalid
   */
  public void loadItemsFromJson(String path) throws IOException {

    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = mapper.readTree(new File(path));
    JsonNode itemsNode = root.path("itemSummaries");

    for (JsonNode node : itemsNode) {
      String id = node.path("itemId").asText();
      String name = node.path("title").asText();
      double price = Double.parseDouble(node.path("price").path("value").asText());
      int storage = (int)(Math.random() * 30) + 1;

      Item item = new Item(id, name, price, storage);
      itemList.add(item);
    }
  }

  /**
   * Returns the list of items that were loaded from the JSON file.
   *
   * @return a list of {@link Item} objects
   */
  public List<Item> getItemList() {
    return itemList;
  }
}

