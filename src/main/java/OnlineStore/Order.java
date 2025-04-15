package OnlineStore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Order")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // generate key_id automatically by DB
  private Long id;

  @Column(name = "user_id")
  Long userId;


}
