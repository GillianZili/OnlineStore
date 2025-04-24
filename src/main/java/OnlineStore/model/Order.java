package OnlineStore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import org.hibernate.annotations.CreationTimestamp;


/**
 * Represents an order in the online store. This class contains information about an order, such as
 * its ID, owner_name, owner_id, total price, amount and time. It is mapped to the "Orders" table in
 * the database.
 */

@Entity
@Table(name = "Orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // generate key_id automatically by DB
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "user_name")
  private String userName;

  private double totalPrice;
  private int totalAmountOfItems;

  @CreationTimestamp
  @Column(name = "checkout_time", updatable = false)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime checkoutTime;

  public Order(){}

  public Order(Long userId, String userName, double totalPrice, int totalAmountOfItems) {
    this.userId = userId;
    this.userName = userName;
    this.totalPrice = totalPrice;
    this.totalAmountOfItems = totalAmountOfItems;
    this.checkoutTime = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public int getTotalAmountOfItems() {
    return totalAmountOfItems;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public void setTotalAmountOfItems(int totalAmountOfItems) {
    this.totalAmountOfItems = totalAmountOfItems;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public LocalDateTime getCheckoutTime() {
    return checkoutTime;
  }

  public void setCheckoutTime(LocalDateTime checkoutTime) {
    this.checkoutTime = checkoutTime;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Double.compare(totalPrice, order.totalPrice) == 0
        && totalAmountOfItems == order.totalAmountOfItems && Objects.equals(id, order.id)
        && Objects.equals(userId, order.userId) && Objects.equals(userName,
        order.userName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, userName, totalPrice, totalAmountOfItems);
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", userId=" + userId +
        ", userName='" + userName + '\'' +
        ", totalPrice=" + totalPrice +
        ", totalAmountOfItems=" + totalAmountOfItems+
        '}';
  }
}
