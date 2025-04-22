package OnlineStore.model;

public class CartRequest {

  public Long user_id;
  public String item_id;
  public int amount;

  public CartRequest() {}

  public Cart toCart() {
    Cart cart = new Cart();
    cart.setUser_id(user_id);
    cart.setItem_id(item_id);
    cart.setAmount(amount);
    return cart;
  }
}
