package OnlineStore.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTest {

  Order order1;
  Order order2;
  Order order3;

  @BeforeEach
  void setUp() {
    order1 = new Order(1L,"Gillian",100.03,8);
    order2 = new Order(2L,"Ziliang",214.15,14);
    order3 = new Order(1L,"Gillian",100.03,8);
  }

  @Test
  void getId() {
    order1.setId(3L);
    assertEquals(3L,order1.getId());
    order2.setId(4L);
    assertEquals(4L,order2.getId());
  }

  @Test
  void getUserId() {
    order1.setUserId(3L);
    assertEquals(3L,order1.getUserId());
    order2.setUserId(4L);
    assertEquals(4L,order2.getUserId());
  }

  @Test
  void getTotalPrice() {
    order1.setTotalPrice(30.65);
    assertEquals(30.65,order1.getTotalPrice());
    order2.setTotalPrice(321.54);
    assertEquals(321.54,order2.getTotalPrice());
  }

  @Test
  void getTotalAmountOfItems() {
    order1.setTotalAmountOfItems(32);
    assertEquals(32,order1.getTotalAmountOfItems());
    order2.setTotalAmountOfItems(23);
    assertEquals(23,order2.getTotalAmountOfItems());
  }

  @Test
  void getUserName() {
    order1.setUserName("Alice");
    assertEquals("Alice",order1.getUserName());
    order2.setUserName("Alex");
    assertEquals("Alex",order2.getUserName());
  }

  @Test
  void testEquals() {
    assertTrue(order1.equals(order3));
    assertFalse(order1.equals(order2));
  }

  @Test
  void testHashCode() {
    assertEquals(order1.hashCode(),order3.hashCode());
    assertNotEquals(order1.hashCode(),order2.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Order{id=null, userId=1, userName='Gillian', totalPrice=100.03, totalAmountOfItems=8}",order1.toString());
    assertEquals("Order{id=null, userId=2, userName='Ziliang', totalPrice=214.15, totalAmountOfItems=14}",order2.toString());
  }
}