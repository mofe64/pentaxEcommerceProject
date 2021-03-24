package com.pentax.ecommerce.services;

import com.pentax.ecommerce.dtos.LoginRequest;
import com.pentax.ecommerce.dtos.UserDTO;
import com.pentax.ecommerce.models.Address;
import com.pentax.ecommerce.models.User;
import com.pentax.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO registerBuyer(UserDTO buyerDTO) {
        User buyer = UserDTO.unpackDTO(buyerDTO);
        User savedBuyer = registerABuyer(buyer);
        UserDTO savedBuyerDTO = UserDTO.packDTO(savedBuyer);
        return savedBuyerDTO;

    }
    private User registerABuyer(User buyer) {
        return userRepository.save(buyer);
    }

    @Override
    public UserDTO registerSeller(UserDTO sellerDTO) {
        User seller = UserDTO.unpackDTO(sellerDTO);
        User savedSeller = registerASeller(seller);
        UserDTO savedSellerDTO = UserDTO.packDTO(savedSeller);
        return savedSellerDTO;
    }

    private User registerASeller(User seller){
        return userRepository.save(seller);
    }

    @Override
    public void login(LoginRequest loginDetails) {

    }
}
