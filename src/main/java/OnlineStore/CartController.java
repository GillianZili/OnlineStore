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
class EmployeeController {

  private final CartRepository repository;

  EmployeeController(CartRepository repository) {
    this.repository = repository;
  }

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/cart")
  List<CartItem> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/cart")
  CartItem newItem(@RequestBody CartItem newItem) {
    return repository.save(newItem);
  }

  // Single item

  @GetMapping("/cart/{id}")
  CartItem one(@PathVariable Long id) {

    return repository.findById(id)
        .orElseThrow(() -> new CartNotFoundException(id));
  }

  @PutMapping("/cart/{id}")
  CartItem replaceEmployee(@RequestBody CartItem newItem, @PathVariable Long id) {

    return repository.findById(id)
        .map(item -> {
          item.setId(item.getId());
          item.setName(item.getName());
          item.setPrice(item.getPrice());
          item.setStorage(item.getStorage());
          return repository.save(newItem);
        })
        .orElseGet(() -> {
          return repository.save(newItem);
        });
  }

  @DeleteMapping("/cart/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
