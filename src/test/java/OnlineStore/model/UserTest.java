package OnlineStore.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

  User user1;
  User user2;
  User user3;

  @BeforeEach
  void setUp() {
    user1 = new User(1L,"Gillian");
    user2 = new User(2L,"Ziliang");
    user3 = new User(1L,"Gillian");
  }

  @Test
  void testEquals() {
    assertFalse(user1.equals(user2));
    assertTrue(user1.equals(user3));
  }

  @Test
  void testHashCode() {
    assertEquals(user1.hashCode(),user3.hashCode());
    assertNotEquals(user1.hashCode(),user2.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("User{id=1, name='Gillian'}",user1.toString());
    assertEquals("User{id=2, name='Ziliang'}",user2.toString());
  }

  @Test
  void getId() {
    user1.setId(3L);
    assertEquals(3L,user1.getId());
    user2.setId(4L);
    assertEquals(4L,user2.getId());
  }

  @Test
  void getName() {
    user1.setName("jgefvd");
    assertEquals("jgefvd",user1.getName());
    user2.setName("skdfgbj");
    assertEquals("skdfgbj",user2.getName());
  }
}