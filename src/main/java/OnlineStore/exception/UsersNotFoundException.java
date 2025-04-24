/**
 * Exception thrown when a specific user can not be found in the user table.
 */
package OnlineStore.exception;

public class UsersNotFoundException extends RuntimeException {

  /**
   * Constructs a new {@code UsersNotFoundException} with a detail message
   * indicating the user ID.
   * @param id the id of a user
   */
  public UsersNotFoundException(Long id) {
    super("Could not find user: " + id);
  }
}
