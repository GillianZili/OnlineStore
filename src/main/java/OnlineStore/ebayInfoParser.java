package OnlineStore;

import java.util.List;
import java.util.Map;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;

public class ebayInfoParser implements JsonParser {

  @Override
  public Map<String, Object> parseMap(String json) throws JsonParseException {
    return Map.of();
  }

  @Override
  public List<Object> parseList(String json) throws JsonParseException {
    return List.of();
  }
}
