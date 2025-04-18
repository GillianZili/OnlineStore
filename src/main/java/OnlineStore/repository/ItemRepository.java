package OnlineStore.repository;

import OnlineStore.model.Item;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
  List<Item> findByNameContainingIgnoreCase(String name);
}
