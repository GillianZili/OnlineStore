package OnlineStore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import OnlineStore.model.Cart;
import OnlineStore.model.Item;
import OnlineStore.model.User;
import OnlineStore.repository.CartRepository;
import OnlineStore.repository.ItemRepository;
import OnlineStore.repository.UserRepository;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link CartService} using Mockito to mock dependencies.
 * <p>
 * This test class focuses on verifying the behavior of updating existing items in the user's cart,
 * including quantity increases and decreases, and edge case handling for insufficient stock or removal.
 */
@ExtendWith(MockitoExtension.class)
class CartServiceTest {

  @Mock
  private UserRepository usersRepo;

  @Mock
  private ItemRepository itemRepo;

  @Mock
  private CartRepository cartRepo;

  @InjectMocks
  private CartService cartService;

  /**
   * Initializes mocks before each test case.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Tests updating an existing item in the cart:
   * <ul>
   *   <li>Decreasing quantity in cart</li>
   *   <li>Increasing quantity in cart</li>
   *   <li>Handling error when trying to remove more than present in cart</li>
   *   <li>Handling error when adding more than available stock</li>
   * </ul>
   */
  @Test
  void testUpdateExistingCartItem() {
    User user = new User(1L);
    Item item1 = new Item("aa0001", "Coke Cola", 3.88, 10);

    Cart cart = new Cart(user.getId(), item1.getName(), item1.getId(), 5, 3.88);

    when(usersRepo.findById(user.getId())).thenReturn(Optional.of(user));
    when(itemRepo.findById(item1.getId())).thenReturn(Optional.of(item1));
    when(cartRepo.findByItemIdAndUserId(item1.getId(), user.getId())).thenReturn(Optional.of(cart));

    // Decrease quantity
    cartService.updateItemInCart(user.getId(), item1.getId(), -3);
    // Increase quantity
    cartService.updateItemInCart(user.getId(), item1.getId(), 2);

    // Verify update
    verify(cartRepo, atLeastOnce()).save(cart);
    assertEquals(4, cart.getAmount());
    assertEquals(11, item1.getStorage());

    // Attempt to remove too many
    IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
        () -> cartService.updateItemInCart(user.getId(), item1.getId(), -5));
    assertEquals("You didn't add that many to your cart.", exception1.getMessage());

    // Attempt to add beyond stock
    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
        () -> cartService.updateItemInCart(user.getId(), item1.getId(), 20));
    assertEquals("Not enough stock", exception2.getMessage());
  }
}

