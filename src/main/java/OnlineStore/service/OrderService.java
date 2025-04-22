package OnlineStore.service;

import OnlineStore.model.Cart;
import OnlineStore.model.Order;
import OnlineStore.model.User;
import OnlineStore.repository.CartRepository;
import OnlineStore.repository.OrderRepository;
import OnlineStore.repository.UsersRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private OrderRepository orderRepo;
  private CartRepository cartRepo;
  private UsersRepository usersRepo;

  public OrderService(OrderRepository orderRepo, CartRepository cartRepo,
      UsersRepository usersRepo) {
    this.orderRepo = orderRepo;
    this.cartRepo = cartRepo;
    this.usersRepo = usersRepo;
  }

  public void generateOrder(Long user_id){
    List<Cart> cartItems = cartRepo.findByUserId(user_id);
    double total_price = 0;
    int total_amount = 0;
    for (Cart cartItem : cartItems) {
      total_price += cartItem.getPrice() * cartItem.getAmount();
      total_amount += cartItem.getAmount();
    }
    double total_price_2f = new BigDecimal(total_price)
        .setScale(2, RoundingMode.HALF_UP)
        .doubleValue();
    Optional<User> user = usersRepo.findById(user_id);
    Order order = new Order(user_id, user.get().getName(), total_price_2f, total_amount);
    orderRepo.save(order);
    cartRepo.deleteById(user_id);
  }

  public void deleteOrder(int order_id){
    orderRepo.deleteById(order_id);
  }
}
