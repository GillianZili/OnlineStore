package OnlineStore.controller;

import OnlineStore.exception.ItemNotFoundException;
import OnlineStore.repository.ItemRepository;
import OnlineStore.model.Item;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

  private final ItemRepository repository;

  ItemController(ItemRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/item")
  List<Item> all() {
    return repository.findAll();
  }

  @PostMapping("/item/add")
  Item newItem(@RequestBody Item newItem) {
    return repository.save(newItem);
  }

  @GetMapping("/item/id/{id}")
  Item getItemById(@PathVariable String id) {
    return repository.findById(id)
        .orElseThrow(() -> new ItemNotFoundException(id));
  }

  @GetMapping("/item/search/{name}")
  List<Item> getItemByName(@PathVariable String name) {
    System.out.println("Search name = " + name);
    return repository.findByNameContainingIgnoreCase(name);
  }

  @PutMapping("/item/update/{id}")
  Item replaceItem(@RequestBody Item newItem, @PathVariable String id) {

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
}
