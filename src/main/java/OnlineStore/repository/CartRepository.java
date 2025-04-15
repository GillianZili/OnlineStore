package OnlineStore.repository;

import OnlineStore.model.Cart;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

  public List<Cart> findByUserId(Long userId);

  public Optional<Cart> findByItemIdAndUserId(String itemId, Long userId);
}
