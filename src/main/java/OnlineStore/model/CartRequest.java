package OnlineStore.model;

/**
 * A request object representing a cart action sent from the client, typically used for adding or
 * updating items in a user's cart.
 */
public class CartRequest {

  /**
   * The ID of the user associated with this cart request.
   */
  public Long user_id;

  /**
   * The ID of the item to be added or updated in the cart.
   */
  public String item_id;

  /**
   * The quantity of the item in the cart.
   */
  public int amount;

  /**
   * Default constructor for serialization/deserialization.
   */
  public CartRequest() {
  }

  /**
   * Convert this request object into a {@link Cart} object.
   *
   * @return a {@link Cart} object populated with the data from this request
   */
  public Cart toCart() {
    Cart cart = new Cart();
    cart.setUser_id(user_id);
    cart.setItem_id(item_id);
    cart.setAmount(amount);
    return cart;
  }
}

