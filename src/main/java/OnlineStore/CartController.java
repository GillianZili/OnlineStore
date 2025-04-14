package OnlineStore;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

  private final CartRepository repository;
  private final CartService service;

  public CartController(CartRepository repository, CartService service) {
    this.repository = repository;
    this.service = service;
  }

  /**
   * check all the information in cart table
   *
   * @return
   */
  @GetMapping("/carts")
  List<Cart> all() {
    return repository.findAll();
  }

  /**
   * check the info of a specific user's cart
   *
   * @return
   */
  @GetMapping("/carts/{user_id}")
  Cart one(@PathVariable Long user_id) {
    return repository.findById(user_id).orElseThrow(() -> new UsersNotFoundException(user_id));
  }

  /**
   * add product to one's cart
   *
   * @param newItem_id the product to be added to the cart
   * @return
   */
  @PostMapping("/carts/{user_id}/{item_id}/{amount}")
  ResponseEntity<String> add(@PathVariable Long user_id, @PathVariable String newItem_id,@PathVariable int amount) {
    try {
      service.updateItemInCart(user_id, newItem_id,amount);
      return ResponseEntity.ok("Item added successfully.");
    } catch (ItemNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
