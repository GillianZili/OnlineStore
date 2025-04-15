package OnlineStore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "Carts")
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // generate key_id automatically by DB
  private Long id;

  @Column(name = "item_id")
  String itemId;

  @Column(name = "user_id")
  Long userId;

  String item_name;
  Integer amount;
  double price;

  public Cart(){}

  public Cart(Long userId, String item_name, String itemId, Integer amount, double price) {
    this.userId = userId;
    this.item_name = item_name;
    this.itemId = itemId;
    this.amount = amount;
    this.price = price;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Item)) {
      return false;
    }
    Cart other = (Cart) o;
    return this.userId.equals(other.userId) && this.item_name.equals(other.item_name) && this.itemId.equals(other.itemId) && this.amount.equals(
        other.amount) && this.price == other.price;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.userId, this.item_name, this.itemId, this.amount, this.price);
  }

  @Override
  public String toString() {
    return "Cart{" +
        "user_id=" + userId +
        ", item_id=" + itemId +
        ", item=" + item_name +
        ", amount=" + amount +
        ", price=" + price +
        '}';
  }

  public void setUser_id(Long userId) {
    this.userId = userId;
  }


  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Long getUser_id() {
    return userId;
  }

  public void setItem_id(String item_id) {
    this.itemId = item_id;
  }

  public void setItem_name(String item_name) {
    this.item_name = item_name;
  }

  public Integer getAmount() {
    return amount;
  }

  public double getPrice() {
    return price;
  }

  public String getItem_id() {
    return itemId;
  }

  public String getItem_name() {
    return item_name;
  }
}
