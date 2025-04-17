package OnlineStore.controller;

import OnlineStore.model.CartRequest;
import OnlineStore.repository.CartRepository;
import OnlineStore.service.CartService;
import OnlineStore.model.Cart;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  @GetMapping("/cart")
  List<Cart> all() {
    List<Cart> carts = repository.findAll();
    if (carts == null) {
      return Collections.emptyList();
    }
    return carts;
  }

  /**
   * check the info of a specific user's cart
   *
   * @return
   */
  @GetMapping("/cart/{user_id}")
  List<Cart> one(@PathVariable Long user_id) {
    List<Cart> carts = repository.findByUserId(user_id);;
    if (carts == null) {
      return Collections.emptyList();
    }
    return carts;
  }


  @PostMapping("/cart/add")
  ResponseEntity<String> add(@RequestBody CartRequest cartRequest) {
    try {
      Cart updateCartItem = cartRequest.toCart();
      service.updateItemInCart(updateCartItem.getUser_id(), updateCartItem.getItem_id(),
          updateCartItem.getAmount());
      return ResponseEntity.ok("Item updated successfully.");
    }catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error: " + e.getMessage());
    }
  }
}
