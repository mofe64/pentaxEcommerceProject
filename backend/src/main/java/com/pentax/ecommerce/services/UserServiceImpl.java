package com.pentax.ecommerce.services;

import com.pentax.ecommerce.dtos.LoginRequest;
import com.pentax.ecommerce.dtos.Token;
import com.pentax.ecommerce.dtos.UserDTO;
import com.pentax.ecommerce.exceptions.AuthenticationException;
import com.pentax.ecommerce.exceptions.UserRoleNotFoundException;
import com.pentax.ecommerce.models.Address;
import com.pentax.ecommerce.models.Role;
import com.pentax.ecommerce.models.User;
import com.pentax.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDTO registerBuyer(UserDTO buyerDTO) {
        User buyer = UserDTO.unpackDTO(buyerDTO);
        Role buyerRole = null;
        try {
            buyerRole = roleService.findByName("BUYER");
        } catch (UserRoleNotFoundException userRoleNotFoundException){
            userRoleNotFoundException.printStackTrace();
        }
        if(buyerRole != null) {
            buyer.getRoles().add(buyerRole);
        }
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
        Role sellerRole = null;
        try {
            sellerRole = roleService.findByName("SELLER");
        } catch (UserRoleNotFoundException userRoleNotFoundException){
            userRoleNotFoundException.printStackTrace();
        }
        if(sellerRole!=null) {
            seller.getRoles().add(sellerRole);
        }
        User savedSeller = registerASeller(seller);
        UserDTO savedSellerDTO = UserDTO.packDTO(savedSeller);
        return savedSellerDTO;
    }

    private User registerASeller(User seller){
        return userRepository.save(seller);
    }

    @Override
    public Token login(LoginRequest loginDetails) throws AuthenticationException {
        String email = loginDetails.getEmail();
        Optional<User> userOptional = findUserByEmail(email);
        if(userOptional.isPresent()){
            User currentUser = userOptional.get();
            String userPassword = loginDetails.getPassword();
            if(userPassword.equals(currentUser.getPassword())){
                return new Token("SUCCESS" + currentUser.getFirstname());
            } else {
                throw new AuthenticationException("Incorrect email or password");
            }
        }else {
            throw  new AuthenticationException("Incorrect email or password");
        }

    }

    private Optional<User> findUserByEmail(String email){
      Optional<User> userOptional =  userRepository.findUserByEmail(email);
      return  userOptional;
    }
}
