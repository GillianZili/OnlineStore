package OnlineStore;

import jakarta.persistence.Table;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Table(name = "Users")
public class Users {

  private @Id
  Long id;
  private String name;

  Users(){}

  Users(Long id){
    this.id = id;
  }

  Users(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Item))
      return false;
    Users other = (Users) o;
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
