package OnlineStore.model;

import jakarta.persistence.Table;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Represents a user in the online store.
 * This class contains information about an item, such as its ID and name.
 * It is mapped to the "Users" table in the database.
 */

@Entity
@Table(name = "Users")
public class User {

  private @Id
  Long id;
  private String name;

  public User(){}

  public User(Long id){
    this.id = id;
  }

  public User(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Item))
      return false;
    User other = (User) o;
    return this.id.equals(other.id) && this.name.equals(other.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name);
  }

  @Override
  public String toString() {
    return "User{" + "id=" + this.id + ", name='" + this.name + '\''+'}';
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
}
