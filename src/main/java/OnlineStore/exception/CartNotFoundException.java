/**
 * Exception thrown when a specific cart cannot be found in a user's cart.
 */
package OnlineStore.exception;

public class CartNotFoundException extends RuntimeException {

  /**
   * Constructs a new {@code CartNotFoundException} with a detail message
   * indicating the user ID and the missing item ID.
   *
   * @param userId the ID of the user whose cart was queried
   * @param itemId the ID of the item that could not be found in the user's cart
   */
  public CartNotFoundException(Long userId, String itemId) {
    super("Could not find the item: " + itemId + "int the cart of user: " + userId);
  }
}
