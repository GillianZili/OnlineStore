package OnlineStore.controller;

import OnlineStore.model.Cart;
import OnlineStore.model.Order;
import OnlineStore.model.User;
import OnlineStore.repository.CartRepository;
import OnlineStore.repository.OrderRepository;
import OnlineStore.repository.UsersRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  private OrderRepository orderRepository;
  private CartRepository cartRepository;
  private UsersRepository usersRepository;

  @Autowired
  public OrderController(OrderRepository orderRepository, CartRepository cartRepository,
      UsersRepository usersRepository) {
    this.orderRepository = orderRepository;
    this.cartRepository = cartRepository;
    this.usersRepository = usersRepository;
  }

  @GetMapping("/order")
  List<Order> all() {
    List<Order> orders = orderRepository.findAll();
    if (orders == null) {
      return Collections.emptyList();
    }
    return orders;
  }

  @GetMapping("/order/{user_id}")
  Optional<Order> one(@PathVariable Long user_id) {
    return orderRepository.findByUserId(user_id);
  }

  // delete cart
  @PostMapping("/checkout/{user_id}")
  ResponseEntity<String> checkOut(@PathVariable Long user_id) {
    try {
      List<Cart> cartItems = cartRepository.findByUserId(user_id);
      double total_price = 0;
      int total_amount = 0;
      for (Cart cartItem : cartItems) {
        total_price += cartItem.getPrice() * cartItem.getAmount();
        total_amount += cartItem.getAmount();
      }

      Optional<User> user = usersRepository.findById(user_id);
      Order order = new Order(user_id, user.get().getName(), total_price, total_amount);
      orderRepository.save(order);
      return ResponseEntity.ok("Check out successfully.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error: " + e.getMessage());
    }
  }
}
