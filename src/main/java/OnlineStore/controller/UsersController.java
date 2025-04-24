package OnlineStore.controller;

import OnlineStore.exception.UsersNotFoundException;
import OnlineStore.repository.UserRepository;
import OnlineStore.model.User;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that handles operations related to users.
 */
@RestController
public class UsersController {

  private final UserRepository userRepo;

  /**
   * Construct a user Controller with specific user repository.
   *
   * @param userRepo the repository used for accessing user data
   */
  public UsersController(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  /**
   * Get a list of all the users.
   *
   * @return a list of user objects
   */
  @GetMapping("/user")
  public List<User> all() {
    return userRepo.findAll();
  }

  /**
   * Register a user with id and name.
   *
   * @param id the id of the user to be added
   * @param name the name of the user to be added
   * @return a {@link ResponseEntity} with a success message if creation succeeds;
   */
  @PostMapping("/user/{id}/{name}")
  public ResponseEntity<String>  newUser(@PathVariable Long id,@PathVariable String name) {
    userRepo.save(new User(id,name));
      return ResponseEntity.ok("User " + name + " created successfully.");
  }

  /**
   * Retrieve a use by his id.
   *
   * @param id the id of the user to be retrieved
   * @return the specific user
   */
  @GetMapping("/user/{id}")
  public User one(@PathVariable Long id) {
    return userRepo.findById(id)
        .orElseThrow(() -> new UsersNotFoundException(id));
  }

  /**
   * Delete the user by its id.
   *
   * @param id the id of the user to be deleted
   * @return a {@link ResponseEntity} with a success message if deletion succeeds;
   */
  @DeleteMapping("/user/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    userRepo.deleteById(id);
    return ResponseEntity.ok("User with id " + id + " deleted successfully.");
  }
}
