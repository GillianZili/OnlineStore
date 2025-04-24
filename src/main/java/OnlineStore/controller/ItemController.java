package OnlineStore.controller;

import OnlineStore.repository.ItemRepository;
import OnlineStore.model.Item;
import java.util.List;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST controller that handles operations related to items.
 */
@RestController
public class ItemController {

  private final ItemRepository ItemRepo;

  /**
   * Construct an item controller with specified item repository.
   *
   * @param ItemRepo the repository used for accessing item data
   */
  public ItemController(ItemRepository ItemRepo) {
    this.ItemRepo = ItemRepo;
  }

  /**
   * Retrieve the information of all the items.
   *
   * @return a list of item objects
   */
  @GetMapping("/item")
  public List<Item> all() {
    return ItemRepo.findAll();
  }

  /**
   * Create an item in item table.
   *
   * @param newItem a new item to be added to the table
   * @return a {@link ResponseEntity} indicating the result of the update operation
   */
  @PostMapping("/item/add")
  public ResponseEntity<String> newItem(@RequestBody Item newItem) {
    try {
      ItemRepo.save(newItem);
      return ResponseEntity.ok("All items updated successfully.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("error: " + e.getMessage());
    }
  }

  /**
   * Retrieve an item by its ID.
   *
   * @param id the unique identifier of the item
   * @return an {@link Optional} containing the item if found, or empty if not found
   */
  @GetMapping("/item/id/{id}")
  public Optional<Item> getItemById(@PathVariable String id) {
    return ItemRepo.findById(id);
  }

  /**
   * Search for an item in item table.
   *
   * @param name the partial or full name to search for
   * @return a list of {@link Item} objects whose names match the search criteria
   */
  @GetMapping("/item/search/{name}")
  public List<Item> getItemByName(@PathVariable String name) {
    return ItemRepo.findByNameContainingIgnoreCase(name);
  }

  /**
   * Update an existing item with new values or creates a new item if the specified ID is not found.
   *
   * @param newItem the {@link Item} object containing updated values
   * @param id the ID of the item to be updated
   * @return the saved {@link Item} object after update or creation
   */
  @PutMapping("/item/update/{id}")
  public Item replaceItem(@RequestBody Item newItem, @PathVariable String id) {

    return ItemRepo.findById(id)
        .map(item -> {
          item.setName(newItem.getName());
          item.setPrice(newItem.getPrice());
          item.setStorage(newItem.getStorage());
          return ItemRepo.save(item);
        })
        .orElseGet(() -> {
          return ItemRepo.save(newItem);
        });
  }
}
