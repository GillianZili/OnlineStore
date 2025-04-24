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

/**
 * REST controller that handles operations related to shopping carts.
 */
@RestController
public class CartController {

  private final CartRepository cartRepo;
  private final CartService cartService;

  /**
   * Construct a cart controller with the specified repository and service.
   *
   * @param cartRepo the repository used for accessing cart data
   * @param cartService the service used to perform business logic on cart items
   */
  public CartController(CartRepository cartRepo, CartService cartService) {
    this.cartRepo = cartRepo;
    this.cartService = cartService;
  }

  /**
   * Check all the information in cart table.
   *
   * @return a list of all cart objects, or an empty list if no cart is found
   */
  @GetMapping("/cart")
  List<Cart> all() {
    List<Cart> carts = cartRepo.findAll();
    if (carts == null) {
      return Collections.emptyList();
    }
    return carts;
  }

  /**
   * Retrieve the cart items associated with a specific user.
   *
   * @return a list of carts belong to a user, or an empty list if no cart belongs to the user
   */
  @GetMapping("/cart/{user_id}")
  List<Cart> one(@PathVariable Long user_id) {
    List<Cart> carts = cartRepo.findByUserId(user_id);;
    if (carts == null) {
      return Collections.emptyList();
    }
    return carts;
  }


  /**
   * Update multiple cart items at once.
   *
   * @param cartRequests a list of objects containing update information
   * @return a {@link ResponseEntity} indicating the result of the update operation
   */
  @PostMapping("/cart/update")
  public ResponseEntity<String> updateCart(@RequestBody List<CartRequest> cartRequests) {
    try {
      for (CartRequest request : cartRequests) {
        Cart updateCartItem = request.toCart();
        cartService.updateItemInCart(
            updateCartItem.getUser_id(),
            updateCartItem.getItem_id(),
            updateCartItem.getAmount()
        );
      }
      return ResponseEntity.ok("All items updated successfully.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("error: " + e.getMessage());
    }
  }
}
