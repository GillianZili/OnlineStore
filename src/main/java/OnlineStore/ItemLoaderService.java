package OnlineStore;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemLoaderService {

  private List<Item> itemList = new ArrayList<>();

  public void loadItemsFromJson(String path) throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    JsonNode root = mapper.readTree(new File(path));

    JsonNode itemsNode = root.path("itemSummaries");

    for (JsonNode node : itemsNode) {
      String id = node.path("itemId").asText();
      String name = node.path("title").asText();
      double price = Double.parseDouble(node.path("price").path("value").asText());
      int storage = (int)(Math.random() * 30) + 1;
      String url = node.path("itemWebUrl").asText();

      Item item = new Item(id, name, price, storage);
      itemList.add(item);
    }
  }

  public List<Item> getItemList() {
    return itemList;
  }
}

