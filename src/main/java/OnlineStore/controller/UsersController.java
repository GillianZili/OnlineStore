package OnlineStore.controller;

import OnlineStore.exception.UsersNotFoundException;
import OnlineStore.repository.UsersRepository;
import OnlineStore.model.User;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

  private final UsersRepository repository;

  UsersController(UsersRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/user")
  List<User> all() {
    return repository.findAll();
  }


  @PostMapping("/user/{id}/{name}")
  ResponseEntity<String>  newUser(@PathVariable Long id,@PathVariable String name) {
      repository.save(new User(id,name));
      return ResponseEntity.ok("User " + name + " created successfully.");
  }

  @GetMapping("/user/{id}")
  User one(@PathVariable Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new UsersNotFoundException(id));
  }

  @PutMapping("/user/{id}")
  User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

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
  ResponseEntity<String> deleteUser(@PathVariable Long id) {
    repository.deleteById(id);
    return ResponseEntity.ok("User with id " + id + " deleted successfully.");
  }
}
