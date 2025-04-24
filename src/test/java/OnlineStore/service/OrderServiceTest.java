package OnlineStore.service;

import static org.junit.jupiter.api.Assertions.*;

import OnlineStore.repository.CartRepository;
import OnlineStore.repository.ItemRepository;
import OnlineStore.repository.OrderRepository;
import OnlineStore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link OrderService} using Mockito to mock dependencies.
 * <p>
 * This test class focuses on verifying the behavior of updating order summary,
 * including generate and delete orders
 */
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

  @Mock
  private OrderRepository orderRepo;

  @Mock
  private CartRepository cartRepo;

  @Mock
  private UserRepository userRepo;

  /**
   * Initializes mocks before each test case.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   *
   */
  @Test
  void generateOrder() {
  }

  @Test
  void deleteOrder() {
  }
}