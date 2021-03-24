package com.pentax.ecommerce.services;

import com.pentax.ecommerce.dtos.LoginRequest;
import com.pentax.ecommerce.dtos.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDTO registerBuyer(UserDTO buyerDetails);
    UserDTO registerSeller(UserDTO sellerDetails);
    void login(LoginRequest loginDetails);
}
