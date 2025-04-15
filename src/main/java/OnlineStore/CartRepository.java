package OnlineStore;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface CartRepository extends JpaRepository<Cart, Long> {

  List<Cart> findByUserId(Long userId);

  Optional<Cart> findByItemIdAndUserId(String itemId, Long userId);
}
