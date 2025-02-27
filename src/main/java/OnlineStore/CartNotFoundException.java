package OnlineStore;

public class CartNotFoundException extends RuntimeException {

  public CartNotFoundException(Long id) {
    super("Could not find item: " + id);;
  }
}
