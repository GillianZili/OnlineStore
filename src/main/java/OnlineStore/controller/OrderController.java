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

@RestController
public class OrderController {

  private OrderRepository orderRepository;
  private OrderService orderService;

  @Autowired
  public OrderController(OrderRepository orderRepository, OrderService orderService) {
    this.orderRepository = orderRepository;
    this.orderService = orderService;
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
  List<Order> one(@PathVariable Long user_id) {
    return orderRepository.findByUserId(user_id);
  }


  @PostMapping("/checkout/{user_id}")
  ResponseEntity<String> checkOut(@PathVariable Long user_id) {
    try {
      orderService.generateOrder(user_id);
      return ResponseEntity.ok("Check out successfully.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("error: " + e.getMessage());
    }
  }

  @DeleteMapping("/order/delete/{order_id}")
  ResponseEntity<String> delete(@PathVariable int order_id) {
    try {
      orderService.deleteOrder(order_id);
      return ResponseEntity.ok("Order No." + order_id + " deleted successfully.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("error: " + e.getMessage());
    }
  }
}
