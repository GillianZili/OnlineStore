package OnlineStore.repository;

import OnlineStore.model.Cart;
import OnlineStore.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
  public Optional<Order> findByUserId(Long userId);
}
