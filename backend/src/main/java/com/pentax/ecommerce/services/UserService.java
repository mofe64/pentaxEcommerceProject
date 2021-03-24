package com.pentax.ecommerce.services;

import com.pentax.ecommerce.dtos.LoginRequest;
import com.pentax.ecommerce.dtos.Token;
import com.pentax.ecommerce.dtos.UserDTO;
import com.pentax.ecommerce.exceptions.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDTO registerBuyer(UserDTO buyerDetails);
    UserDTO registerSeller(UserDTO sellerDetails);
    Token login(LoginRequest loginDetails) throws AuthenticationException;
}
