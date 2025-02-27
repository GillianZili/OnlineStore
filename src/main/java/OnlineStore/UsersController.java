package OnlineStore;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UsersController {

  private final UsersRepository repository;

  UsersController(UsersRepository repository) {
    this.repository = repository;
  }

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/user")
  List<Users> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/user")
  Users newUser(@RequestBody Users newUser) {
    return repository.save(newUser);
  }

  // Single user

  @GetMapping("/user/{id}")
  Users one(@PathVariable Long id) {

    return repository.findById(id)
        .orElseThrow(() -> new UsersNotFoundException(id));
  }

  @PutMapping("/user/{id}")
  Users replaceUser(@RequestBody Users newUser, @PathVariable Long id) {

    return repository.findById(id)
        .map(user -> {
          user.setId(user.getId());
          user.setName(user.getName());
          return repository.save(newUser);
        })
        .orElseGet(() -> {
          return repository.save(newUser);
        });
  }

  @DeleteMapping("/user/{id}")
  void deleteUser(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
