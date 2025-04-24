package OnlineStore.repository;

import OnlineStore.model.Cart;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and managing {@link Cart} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations.
 */
public interface CartRepository extends JpaRepository<Cart, Long> {

  /**
   * Finds all cart entries that belong to a specific user.
   *
   * @param userId the ID of the user
   * @return a list of carts belonging to the user
   */
  public List<Cart> findByUserId(Long userId);

  /**
   * Finds a specific cart entry by item ID and user ID.
   *
   * @param itemId the ID of the item
   * @param userId the ID of the user
   * @return an Optional containing the matching cart entry, if found
   */
  public Optional<Cart> findByItemIdAndUserId(String itemId, Long userId);
}

