package OnlineStore.controller;

import OnlineStore.model.Order;
import OnlineStore.repository.OrderRepository;
import OnlineStore.service.OrderService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that handles operations related to orders.
 */
@RestController
public class OrderController {

  private OrderRepository orderRepo;
  private OrderService orderService;

  /**
   * Construct an order controller with specified order repository and order service.
   *
   * @param orderRepo the repository used for accessing order data
   * @param orderService the service used to perform business logic on orders
   */
  @Autowired
  public OrderController(OrderRepository orderRepo, OrderService orderService) {
    this.orderRepo = orderRepo;
    this.orderService = orderService;
  }

  /**
   * Retrieve all orders from the database.
   *
   * @return a list of all {@link Order} records; returns an empty list if no orders are found
   */
  @GetMapping("/order")
  public List<Order> all() {
    List<Order> orders = orderRepo.findAll();
    if (orders == null) {
      return Collections.emptyList();
    }
    return orders;
  }

  /**
   * Retrieve all orders placed by a specific user.
   *
   * @param user_id the ID of the user whose orders are to be retrieved
   * @return a list of {@link Order} records associated with the given user ID
   */
  @GetMapping("/order/{user_id}")
  public List<Order> one(@PathVariable Long user_id) {
    return orderRepo.findByUserId(user_id);
  }

  /**
   * Initiate the checkout process for a given user, generating a new order based on their cart.
   *
   * @param user_id the ID of the user performing checkout
   * @return a {@link ResponseEntity} with a success message if the order was created successfully;
   *         otherwise, returns an error message with HTTP 500 status
   */
  @PostMapping("/checkout/{user_id}")
  public ResponseEntity<String> checkOut(@PathVariable Long user_id) {
    try {
      orderService.generateOrder(user_id);
      return ResponseEntity.ok("Check out successfully.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("error: " + e.getMessage());
    }
  }

  /**
   * Delete an order by order_id.
   *
   * @param order_id the ID of the order to be deleted
   * @return a {@link ResponseEntity} with a success message if deletion succeeds;
   *         otherwise, returns an error message with HTTP 500 status
   */
  @DeleteMapping("/order/delete/{order_id}")
  public ResponseEntity<String> delete(@PathVariable int order_id) {
    try {
      orderService.deleteOrder(order_id);
      return ResponseEntity.ok("Order No." + order_id + " deleted successfully.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("error: " + e.getMessage());
    }
  }
}
