package com.pentax.ecommerce.services;

import com.pentax.ecommerce.dtos.CartDTO;
import com.pentax.ecommerce.dtos.LoginRequest;
import com.pentax.ecommerce.dtos.Token;
import com.pentax.ecommerce.dtos.UserDTO;
import com.pentax.ecommerce.exceptions.AuthenticationException;
import com.pentax.ecommerce.exceptions.CartException;
import com.pentax.ecommerce.exceptions.ProductException;
import com.pentax.ecommerce.exceptions.UserException;
import com.pentax.ecommerce.models.Address;
import com.pentax.ecommerce.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDTO registerBuyer(UserDTO buyerDetails) throws UserException;

    UserDTO registerSeller(UserDTO sellerDetails) throws UserException;

    CartDTO getUserCart(String userId) throws UserException, CartException;

    UserDTO findUserById(String userId) throws UserException;

    void addAddress(String userId, Address address) throws UserException;

    void addProductToCart(String productId, String userId, int quantity) throws UserException, ProductException, CartException;

    void removeProductFromCart(String productId, String userId, int quantity) throws UserException, CartException;

    List<Address> getUserAddresses(String userId) throws UserException;
}