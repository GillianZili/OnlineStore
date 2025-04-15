package OnlineStore.exception;

public class CartNotFoundException extends RuntimeException {

  public CartNotFoundException(Long userId, String itemId) {
    super("Could not find the item: " + itemId + "int the cart of user: " + userId);
  }
}
