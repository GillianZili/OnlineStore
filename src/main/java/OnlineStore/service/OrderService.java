package OnlineStore.service;

import OnlineStore.model.Cart;
import OnlineStore.model.Order;
import OnlineStore.model.User;
import OnlineStore.repository.CartRepository;
import OnlineStore.repository.OrderRepository;
import OnlineStore.repository.UserRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * This service implements generate and delete orders.
 */
@Service
public class OrderService {
  private OrderRepository orderRepo;
  private CartRepository cartRepo;
  private UserRepository usersRepo;

  /**
   * Construct an order service with provided parameters.
   *
   * @param orderRepo the order repository used to access order data
   * @param cartRepo the cart repository used to access order data
   * @param usersRepo the user repository used to access order data
   */
  public OrderService(OrderRepository orderRepo, CartRepository cartRepo,
      UserRepository usersRepo) {
    this.orderRepo = orderRepo;
    this.cartRepo = cartRepo;
    this.usersRepo = usersRepo;
  }

  /**
   * Generate an order summary when checkout, based on the items in the user's cart.
   *
   * @param user_id the id of the user who checks out
   */
  public void generateOrder(Long user_id){
    List<Cart> cartItems = cartRepo.findByUserId(user_id);
    double total_price = 0;
    int total_amount = 0;
    for (Cart cartItem : cartItems) {
      total_price += cartItem.getPrice() * cartItem.getAmount();
      total_amount += cartItem.getAmount();
    }
    // keep 2 decimal places
    double total_price_2f = new BigDecimal(total_price)
        .setScale(2, RoundingMode.HALF_UP)
        .doubleValue();
    Optional<User> user = usersRepo.findById(user_id);
    Order order = new Order(user_id, user.get().getName(), total_price_2f, total_amount);
    orderRepo.save(order);
    cartRepo.deleteById(user_id);
  }

  /**
   * Delete the order according to an order id.
   *
   * @param order_id the id of the order to be deleted
   */
  public void deleteOrder(int order_id){
    orderRepo.deleteById(order_id);
  }
}
