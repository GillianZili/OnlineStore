package OnlineStore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 * Represents a cart in the online store.
 * This class contains information about a cart, such as its ID, owner_name, owner_id, item_id, price and amount.
 * It is mapped to the "Carts" table in the database.
 */

@Entity
@Table(name = "Carts")
public class Cart {

  private @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // generate key_id automatically by DB
  Long id;

  @Column(name = "item_id")
  private String itemId;

  @Column(name = "user_id")
  private Long userId;

  private String item_name;
  private Integer amount;
  private double price;

  /**
   * Construct a cart by default.
   */
  public Cart(){}

  /**
   * Construct a cart by specific information.
   *
   * @param userId the ID of the user who owns this cart
   * @param item_name the name of the item in the cart
   * @param itemId    the unique identifier of the item
   * @param amount    the quantity of the item
   * @param price     the price of the item
   */
  public Cart(Long userId, String item_name, String itemId, Integer amount, double price) {
    this.userId = userId;
    this.item_name = item_name;
    this.itemId = itemId;
    this.amount = amount;
    this.price = price;
  }


  /**
   * Check whether this cart is equal to another object.
   *
   * @param o the object to compare with
   * @return true if the other object is a Cart and has the same values for all fields
   */
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

  /**
   * Generate a hash code for this cart object.
   *
   * @return a hash code based on all fields
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.userId, this.item_name, this.itemId, this.amount, this.price);
  }

  /**
   * Return a string representation of the cart.
   *
   * @return a string containing all field values
   */
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

  /**
   * Set the ID of the user who owns this cart.
   *
   * @param userId the user ID
   */
  public void setUser_id(Long userId) {
    this.userId = userId;
  }


  /**
   * Set the quantity of the item.
   *
   * @param amount the amount to set
   */
  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  /**
   * Set the price of the item.
   *
   * @param price the price to set
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Return the ID of the user who owns this cart.
   *
   * @return the user ID
   */
  public Long getUser_id() {
    return userId;
  }

  /**
   * Set the ID of the item.
   *
   * @param item_id the item ID to set
   */
  public void setItem_id(String item_id) {
    this.itemId = item_id;
  }

  /**
   * Set the name of the item.
   *
   * @param item_name the name to set
   */
  public void setItem_name(String item_name) {
    this.item_name = item_name;
  }

  /**
   * Return the quantity of the item.
   *
   * @return the amount
   */
  public Integer getAmount() {
    return amount;
  }

  /**
   * Return the price of the item.
   *
   * @return the price
   */
  public double getPrice() {
    return price;
  }

  /**
   * Return the ID of the item.
   *
   * @return the item ID
   */
  public String getItem_id() {
    return itemId;
  }

  /**
   * Return the name of the item.
   *
   * @return the item name
   */
  public String getItem_name() {
    return item_name;
  }
}
