package OnlineStore.controller;

import OnlineStore.model.Order;
import OnlineStore.repository.OrderRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  OrderRepository repository;

  @GetMapping("/order")
  List<Order> all(){
    return repository.findAll();
  }

  @GetMapping("/order/{user_id}")
  Order one(@PathVariable Long user_id){
    return repository.findByUserId(user_id);
  }


}
