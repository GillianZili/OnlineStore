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
class ItemController {

  private final ItemRepository repository;

  ItemController(ItemRepository repository) {
    this.repository = repository;
  }

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/item")
  List<Item> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/item")
  Item newItem(@RequestBody Item newItem) {
    return repository.save(newItem);
  }

  // Single item

  @GetMapping("/item/{id}")
  Item one(@PathVariable Long id) {

    return repository.findById(id)
        .orElseThrow(() -> new ItemNotFoundException(id));
  }

  @PutMapping("/item/{id}")
  Item replaceItem(@RequestBody Item newItem, @PathVariable Long id) {

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

  @DeleteMapping("/item/{id}")
  void deleteItem(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
