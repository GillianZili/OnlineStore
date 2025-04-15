package OnlineStore;

import jakarta.persistence.Table;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
@Table(name = "Items")
public class Item {

  private @Id
  String id;
  private String name;
  private double price;
  private int storage;

  Item() {}

  Item(String id) {
    this.id=id;
  }

  Item(String id, String name, double price, int storage) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.storage = storage;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Item)) {
      return false;
    }
    Item other = (Item) o;
    return this.id.equals(other.id) && this.name.equals(other.name)
        && this.price == other.price && this.storage == other.storage;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.price, this.storage);
  }

  @Override
  public String toString() {
    return "Item{" + "id=" + this.id + ", name='" + this.name + '\'' + ", price=" + this.price
        + '\'' + this.storage + '\'' + '}';
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public int getStorage() {
    return storage;
  }

  public void setStorage(int storage) {
    this.storage = storage;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }
}
