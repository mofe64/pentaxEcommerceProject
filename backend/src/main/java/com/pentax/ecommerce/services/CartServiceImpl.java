package com.pentax.ecommerce.services;

import com.pentax.ecommerce.dtos.CartDTO;
import com.pentax.ecommerce.exceptions.CartException;
import com.pentax.ecommerce.exceptions.ProductException;
import com.pentax.ecommerce.models.Cart;
import com.pentax.ecommerce.models.Item;
import com.pentax.ecommerce.models.Product;
import com.pentax.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    @Override
    public Cart createCart() {
      return createNewCart();
    }
    private Cart createNewCart(){
      return  cartRepository.save(new Cart());
    }

    @Override
    public BigDecimal calculateCartTotal(String cartId) throws CartException {
        Cart cart = findCartByTheId(cartId);
        BigDecimal total = BigDecimal.ZERO;
        for (Item cartItem: cart.getItems().values()){
           total= total.add(cartItem.getItemTotal());
        }
        return total;
    }

    @Override
    public void addItemToCart(String productId, int quantity, String cartId) throws CartException, ProductException {
        Product product = productService.findProduct(productId);
        Cart cart = findCartByTheId(cartId);
        cart.addItem(product, quantity);
        saveCart(cart);
    }


    private Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(String cartId, String productId) throws CartException {
        Cart cart = findCartByTheId(cartId);
        cart.removeItem(productId);
        saveCart(cart);
    }
    @Override
    public void reduceCartItemQuantity(String cartId, String productId, int quantity) throws CartException {
        Cart cart = findCartByTheId(cartId);
        cart.removeItem(productId, quantity);
        saveCart(cart);
    }

    private void removeCart(Cart cart) {
    }

    @Override
    public CartDTO findCartById(String cartId) throws CartException {
        Cart cart = findCartByTheId(cartId);
        return CartDTO.packDTO(cart);
    }



    private Cart findCartByTheId(String cartId) throws CartException {
        Optional<Cart> cartOptional = cartRepository.findCartById(cartId);
        if(cartOptional.isPresent()) {
            return cartOptional.get();
        } else {
            throw new CartException("No cart found with that id");
        }
    }
}
