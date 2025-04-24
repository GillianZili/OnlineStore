package OnlineStore.repository;

import OnlineStore.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and managing {@link Order} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations.
 */

public interface OrderRepository extends JpaRepository<Order, Integer> {

  /**
   * Find the orders of a given user.
   *
   * @param userId the id of the user
   * @return a list of order belongs to the user
   */
  public List<Order> findByUserId(Long userId);
}
