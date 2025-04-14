package OnlineStore;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "Carts")
public class Cart {

  Long user_id;
  String item_name;
  Integer amount;
  double price;


  public Cart(Long user_id, String item_name, Integer amount, double price) {
    this.user_id = user_id;
    this.item_name = item_name;
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
    return this.user_id.equals(other.user_id) && this.item_name.equals(other.item_name) && this.amount.equals(
        other.amount) && this.price == other.price;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.user_id, this.item_name, this.amount, this.price);
  }

  @Override
  public String toString() {
    return "Cart{" +
        "user_id=" + user_id +
        ", item=" + item_name +
        ", amount=" + amount +
        ", price=" + price +
        '}';
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public void setItem(String item_name) {
    this.item_name = item_name;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Long getUser_id() {
    return user_id;
  }

  public String getItem() {
    return item_name;
  }

  public Integer getAmount() {
    return amount;
  }

  public double getPrice() {
    return price;
  }
}
