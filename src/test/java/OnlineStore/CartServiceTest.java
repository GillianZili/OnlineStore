package OnlineStore;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import OnlineStore.model.Cart;
import OnlineStore.model.Item;
import OnlineStore.model.Users;
import OnlineStore.repository.CartRepository;
import OnlineStore.repository.ItemRepository;
import OnlineStore.repository.UsersRepository;
import OnlineStore.service.CartService;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

  @Mock
  private UsersRepository usersRepo;

  @Mock
  private ItemRepository itemRepo;

  @Mock
  private CartRepository cartRepo;

  @InjectMocks
  private CartService cartService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testUpdateExistingCartItem() {
    Users user = new Users(1L);
    Item item1 = new Item("aa0001","Coke Cola",3.88,10);

    Cart cart = new Cart(user.getId(), item1.getName(), item1.getId(), 5,3.88);

    when(usersRepo.findById(user.getId())).thenReturn(Optional.of(user));
    when(itemRepo.findById(item1.getId())).thenReturn(Optional.of(item1));
    when(cartRepo.findByItemIdAndUserId(item1.getId(),user.getId())).thenReturn(Optional.of(cart));

    cartService.updateItemInCart(user.getId(), item1.getId(), -3);
    cartService.updateItemInCart(user.getId(), item1.getId(), 2);
    verify(cartRepo,atLeastOnce()).save(cart);

    assertEquals(4, cart.getAmount());
    assertEquals(11, item1.getStorage());

    IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, ()->cartService.updateItemInCart(
        user.getId(),item1.getId(),-5));
    assertEquals("You didn't add that many to your cart.", exception1.getMessage());

    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, ()->cartService.updateItemInCart(
        user.getId(),item1.getId(),20));
    assertEquals("Not enough stock", exception2.getMessage());
  }
}
