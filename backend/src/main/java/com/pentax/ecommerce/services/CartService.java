package com.pentax.ecommerce.services;

import com.pentax.ecommerce.dtos.CartDTO;
import com.pentax.ecommerce.exceptions.CartException;
import com.pentax.ecommerce.exceptions.ProductException;
import com.pentax.ecommerce.models.Cart;
import com.pentax.ecommerce.models.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface CartService {
    public void createCart();
    //Todo get user's cart
    public BigDecimal calculateCartTotal(String cartId) throws CartException;
    public void addItemToCart(Item item, int quantity,String cartId) throws CartException;
    public void removeItemFromCart(String cartId, String productId) throws CartException;
    public CartDTO findCartById(String cartId) throws CartException;
    public void reduceCartItemQuantity(String cartId, String productId, int quantity) throws CartException;
}
