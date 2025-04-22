package OnlineStore.service;

import static java.lang.Math.abs;

import OnlineStore.repository.CartRepository;
import OnlineStore.exception.ItemNotFoundException;
import OnlineStore.repository.ItemRepository;
import OnlineStore.exception.UsersNotFoundException;
import OnlineStore.repository.UsersRepository;
import OnlineStore.model.Cart;
import OnlineStore.model.Item;
import OnlineStore.model.User;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * This service implements adding and deleting items to cart
 */

@Service
public class CartService {

  UsersRepository usersRepo;
  ItemRepository itemRepo;
  CartRepository cartRepo;

  public CartService(ItemRepository itemRepo, CartRepository cartRepo, UsersRepository usersRepo) {
    this.itemRepo = itemRepo;
    this.cartRepo = cartRepo;
    this.usersRepo = usersRepo;
  }

  /**
   * Updates the quantity of a specific item in a user's shopping cart.
   * <p>
   * If the item is not yet in the cart, it will be added. If the updated quantity reaches zero,
   * the item will be removed from the cart. Also updates the stock in the item repository accordingly.
   *
   * @param userId the ID of the user whose cart is being updated
   * @param itemId the ID of the item to update
   * @param amount the number of items to add (positive) or remove (negative) from the cart
   * @throws UsersNotFoundException if the user ID does not exist
   * @throws ItemNotFoundException if the item ID does not exist
   * @throws IllegalArgumentException if the requested stock is insufficient or the removal exceeds what's in the cart
   */
  public void updateItemInCart(Long userId, String itemId, int amount) {
    User user = usersRepo.findById(userId).orElseThrow(() -> new UsersNotFoundException(userId));
    Item item = itemRepo.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
    if (amount>0 && item.getStorage() < amount) {
      throw new IllegalArgumentException("Not enough stock");
    }
    Optional<Cart> optionalCart = cartRepo.findByItemIdAndUserId(itemId,userId);
    if (amount<0 && (optionalCart.get().getAmount() < abs(amount) || optionalCart.isEmpty())) {
      throw new IllegalArgumentException("You didn't add that many to your cart.");
    }


    // if the item doesn't exist in the cart table, create a new one
    // else just update the amount
    // if the amount of the item becomes 0, delete the line
    if (optionalCart.isEmpty()){
      Cart new_item = new Cart(userId,item.getName(),item.getId(),amount,item.getPrice());
      cartRepo.save(new_item);
    }else{
      Cart existing_item=optionalCart.get();
      int new_amount=existing_item.getAmount()+amount;
      if(new_amount==0){
        cartRepo.delete(existing_item);
      }else {
        existing_item.setAmount(existing_item.getAmount() + amount);
        cartRepo.save(existing_item);
      }
    }

    //update the amount of this item in item table
    item.setStorage(item.getStorage() - amount);
    itemRepo.save(item);
  }
}
