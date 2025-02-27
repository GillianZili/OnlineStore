package OnlineStore;

import org.springframework.data.jpa.repository.JpaRepository;

interface CartRepository extends JpaRepository<CartItem, Long> {

}
