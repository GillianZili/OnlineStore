package OnlineStore.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartTest {
   Cart cart1;
   Cart cart2;
   Cart cart3;

  @BeforeEach
  void setUp() {
    cart1 = new Cart(1L,"pudding","pud101",30,5.66);
    cart2 = new Cart(2L,"chocolate bar","choco101",27,3.99);
    cart3 = new Cart(1L,"pudding","pud101",30,5.66);
  }

  @Test
  void testEquals() {
    assertFalse(cart1.equals(cart2));
    assertTrue(cart1.equals(cart3));
  }

  @Test
  void testHashCode() {
    assertEquals(cart1.hashCode(),cart3.hashCode());
    assertNotEquals(cart1.hashCode(),cart2.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Cart{user_id=1, item_id=pud101, item=pudding, amount=30, price=5.66}",cart1.toString());
    assertEquals("Cart{user_id=2, item_id=choco101, item=chocolate bar, amount=27, price=3.99}",cart2.toString());
  }

  @Test
  void getUser_id() {
    cart1.setUser_id(11L);
    assertEquals(11L,cart1.getUser_id());
    cart2.setUser_id(22L);
    assertEquals(22L,cart2.getUser_id());
  }

  @Test
  void getAmount() {
    cart1.setAmount(60);
    assertEquals(60,cart1.getAmount());
    cart2.setAmount(13);
    assertEquals(13,cart2.getAmount());
  }

  @Test
  void getPrice() {
    cart1.setPrice(13.98);
    assertEquals(13.98,cart1.getPrice());
    cart2.setPrice(2.5);
    assertEquals(2.5,cart2.getPrice());
  }

  @Test
  void getItem_id() {
    cart1.setItem_id("asdf1234");
    assertEquals("asdf1234",cart1.getItem_id());
    cart2.setItem_id("ghjk4321");
    assertEquals("ghjk4321",cart2.getItem_id());
  }

  @Test
  void getItem_name() {
    cart1.setItem_name("cake");
    assertEquals("cake",cart1.getItem_name());
    cart2.setItem_name("pancake");
    assertEquals("pancake",cart2.getItem_name());
  }
}