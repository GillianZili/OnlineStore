package OnlineStore.repository;

import OnlineStore.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
  public List<Order> findByUserId(Long userId);
}
