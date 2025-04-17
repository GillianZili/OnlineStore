package OnlineStore.controller;

import OnlineStore.model.Order;
import OnlineStore.repository.OrderRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  OrderRepository repository;

  @GetMapping("/order")
  List<Order> all(){
    return repository.findAll();
  }

  @PostMapping("/order/{user_id}")
  Order one(){
    return repository.findById(user_id);
  }
}
