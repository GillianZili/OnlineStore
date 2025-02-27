package OnlineStore;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
class Item {

  private @Id
  Long id;
  private String name;
  private double price;
  private int storage;

  Item(){}

  Item(Long id, String name, double price, int storage) {
    this.id =id;
    this.name = name;
    this.price = price;
    this.storage = storage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Item))
      return false;
    Item other = (Item) o;
    return this.id.equals(other.id) && this.name.equals(other.name)
        && this.price==other.price && this.storage==other.storage;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.price,this.storage);
  }

  @Override
  public String toString() {
    return "CartItem{" + "id=" + this.id + ", name='" + this.name + '\'' + ", price=" + this.price + '\'' + this.storage + '\''+'}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getStorage() {
    return storage;
  }

  public void setStorage(int storage) {
    this.storage = storage;
  }
}
