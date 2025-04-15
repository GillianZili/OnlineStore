package OnlineStore.exception;

public class UsersNotFoundException extends RuntimeException {

  public UsersNotFoundException(Long id) {
    super("Could not find user: " + id);
  }
}
