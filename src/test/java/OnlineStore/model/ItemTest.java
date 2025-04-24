package OnlineStore.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {

  Item item1;
  Item item2;
  Item item3;

  @BeforeEach
  void setUp() {
    item1 = new Item("pud101","pudding",3.88,30);
    item2 = new Item("choco101","chocolate bar",12.56,24);
    item3 = new Item("pud101","pudding",3.88,30);
  }

  @Test
  void testEquals() {
    assertFalse(item1.equals(item2));
    assertTrue(item1.equals(item3));
  }

  @Test
  void testHashCode() {
    assertEquals(item1.hashCode(),item3.hashCode());
    assertNotEquals(item1.hashCode(),item2.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Item{id=pud101, name='pudding', price=3.88', storage=30'}",item1.toString());
    assertEquals("Item{id=choco101, name='chocolate bar', price=12.56', storage=24'}",item2.toString());
  }

  @Test
  void getId() {
    item1.setId("asdf1234");
    assertEquals("asdf1234",item1.getId());
    item2.setId("ghjk4321");
    assertEquals("ghjk4321",item2.getId());
  }

  @Test
  void getName() {
    item1.setName("cake");
    assertEquals("cake",item1.getName());
    item2.setName("setName");
    assertEquals("setName",item2.getName());
  }

  @Test
  void getPrice() {
    item1.setPrice(3.56);
    assertEquals(3.56,item1.getPrice());
    item2.setPrice(10.24);
    assertEquals(10.24,item2.getPrice());
  }

  @Test
  void getStorage() {
    item1.setStorage(3);
    assertEquals(3,item1.getStorage());
    item2.setStorage(10);
    assertEquals(10,item2.getStorage());
  }
}