/**
 * Exception thrown when a specific item can not be found in the item table.
 */
package OnlineStore.exception;

public class ItemNotFoundException extends RuntimeException {

  /**
   * Constructs a new {@code ItemNotFoundException} with a detail message
   * indicating the item ID.
   * @param id the id of an item
   */
  public ItemNotFoundException(String id) {
    super("Could not find item: " + id);
  }
}
