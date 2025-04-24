package OnlineStore.repository;

import OnlineStore.model.Item;
import OnlineStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and managing {@link User} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}