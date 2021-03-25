package com.pentax.ecommerce.services;

import com.pentax.ecommerce.dtos.LoginRequest;
import com.pentax.ecommerce.dtos.Token;
import com.pentax.ecommerce.dtos.UserDTO;
import com.pentax.ecommerce.exceptions.AuthenticationException;
import com.pentax.ecommerce.exceptions.UserException;
import com.pentax.ecommerce.exceptions.UserRoleNotFoundException;
import com.pentax.ecommerce.models.Address;
import com.pentax.ecommerce.models.Role;
import com.pentax.ecommerce.models.User;
import com.pentax.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadAUserByUsername(username);
    }

    private UserDetails loadAUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    @Override
    public UserDTO registerBuyer(UserDTO buyerDTO) throws UserException {
        User buyer = UserDTO.unpackDTO(buyerDTO);
        if(emailExists(buyer.getEmail())){
            throw new UserException("Email already exists");
        }
        if(usernameExists(buyer.getUsername())){
            throw new UserException("Username already exists");
        }

        Role buyerRole = null;
        try {
            buyerRole = roleService.findByName("BUYER");
        } catch (UserRoleNotFoundException userRoleNotFoundException) {
            userRoleNotFoundException.printStackTrace();
        }
        if (buyerRole != null) {
            buyer.getRoles().add(buyerRole);
        }
        buyer.setPassword(bCryptPasswordEncoder.encode(buyer.getPassword()));
        User savedBuyer = registerABuyer(buyer);
        return UserDTO.packDTO(savedBuyer);
    }

    private User registerABuyer(User buyer) {
        return userRepository.save(buyer);
    }

    @Override
    public UserDTO registerSeller(UserDTO sellerDTO) throws UserException {
        User seller = UserDTO.unpackDTO(sellerDTO);
        if(emailExists(seller.getEmail())){
            throw new UserException("Email already exists");
        }
        if(usernameExists(seller.getUsername())){
            throw new UserException("Username already exists");

        }
        Role sellerRole = null;
        try {
            sellerRole = roleService.findByName("SELLER");
        } catch (UserRoleNotFoundException userRoleNotFoundException) {
            userRoleNotFoundException.printStackTrace();
        }
        if (sellerRole != null) {
            seller.getRoles().add(sellerRole);
        }
        seller.setPassword(bCryptPasswordEncoder.encode(seller.getPassword()));
        User savedSeller = registerASeller(seller);
        return UserDTO.packDTO(savedSeller);
    }

    private User registerASeller(User seller) {
        return userRepository.save(seller);
    }

    private Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    private boolean emailExists(String email){
        return userRepository.findUserByEmail(email).isPresent();
    }
    private boolean usernameExists(String username){
        return userRepository.findUserByUsername(username).isPresent();
    }


}
