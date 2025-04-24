package OnlineStore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import OnlineStore.model.Cart;
import OnlineStore.model.Order;
import OnlineStore.model.User;
import OnlineStore.repository.CartRepository;
import OnlineStore.repository.OrderRepository;
import OnlineStore.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link OrderService} using Mockito to mock dependencies.
 * <p>
 * This test class focuses on verifying the behavior of updating order summary, including generate
 * and delete orders
 */
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

  @Mock
  private OrderRepository orderRepo;

  @Mock
  private CartRepository cartRepo;

  @Mock
  private UserRepository userRepo;

  @InjectMocks
  private OrderService orderService;

  // Create fake users and carts before testing orderService
  List<Cart> cartItems = new ArrayList<>();
  User user;

  /**
   * Initializes mocks before each test case.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    Cart cart1 = new Cart(1L, "pudding", "pud101", 30, 5.66);
    Cart cart2 = new Cart(1L, "chocolate bar", "choco101", 27, 3.99);
    cartItems.add(cart1);
    cartItems.add(cart2);
    user = new User();
    user.setId(1L);
  }

  @Test
  void generateOrder() {
    when(userRepo.findById(1L)).thenReturn(Optional.of(user));
    when(cartRepo.findByUserId(1L)).thenReturn(cartItems);

    orderService.generateOrder(1L);

    // verify if the order summary generated successfully
    ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
    verify(orderRepo, atLeastOnce()).save(orderCaptor.capture());

    Order savedOrder = orderCaptor.getValue();
    assertEquals(1L, savedOrder.getUserId());
    assertEquals("Order{id=null, userId=1, userName='null', totalPrice=277.53, totalAmountOfItems=57}",savedOrder.toString());

    // verify if the cart items are deleted successfully while checking out
    ArgumentCaptor<Long> userIdCaptor = ArgumentCaptor.forClass(Long.class);
    verify(cartRepo, atLeastOnce()).deleteById(userIdCaptor.capture());
    assertEquals(1L, userIdCaptor.getValue());
  }
}