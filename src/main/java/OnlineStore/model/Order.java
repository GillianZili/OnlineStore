package OnlineStore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Order")
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

  public Order(){}

  public Order(Long id, Long userId, String userName, double totalPrice, int totalAmountOfItems) {
    this.id = id;
    this.userId = userId;
    this.userName = userName;
    this.totalPrice = totalPrice;
    this.totalAmountOfItems = totalAmountOfItems;
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
        ", totalAmountOfItems=" + totalAmountOfItems +
        '}';
  }
}
