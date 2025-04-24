package OnlineStore.repository;

import OnlineStore.model.Cart;
import OnlineStore.model.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and managing {@link Item} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations.
 */
public interface ItemRepository extends JpaRepository<Item, String> {

  /**
   * Finds all items whose names contain the given string, case-insensitively.
   *
   * @param name the substring to search for specific items
   * @return a list of matching item objects
   */
  List<Item> findByNameContainingIgnoreCase(String name);
}
