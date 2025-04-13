package OnlineStore;

public class ItemNotFoundException extends RuntimeException {

  public ItemNotFoundException(String id) {
    super("Could not find item: " + id);
  }
}
