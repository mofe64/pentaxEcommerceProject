package com.pentax.ecommerce.controllers;

import com.pentax.ecommerce.dtos.ApiResponse;
import com.pentax.ecommerce.dtos.AuthToken;
import com.pentax.ecommerce.dtos.LoginRequest;
import com.pentax.ecommerce.dtos.UserDTO;
import com.pentax.ecommerce.exceptions.UserException;
import com.pentax.ecommerce.security.jwt.TokenProvider;
import com.pentax.ecommerce.services.RoleService;
import com.pentax.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostMapping("login")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtUtil.generateToken(authentication);
        return new ResponseEntity<>(new AuthToken(token), HttpStatus.OK);
    }

    @PostMapping("register/seller")
    public ResponseEntity<?> registerSeller(@RequestBody UserDTO userDTO){
        try {
            UserDTO newSeller = userService.registerSeller(userDTO);
            return new ResponseEntity<>(newSeller, HttpStatus.OK);
        } catch (UserException userException){
            return new ResponseEntity<>(new ApiResponse(false, userException.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("register/buyer")
    public ResponseEntity<?> registerBuyer(@RequestBody UserDTO userDTO){
        try {
            UserDTO newBuyer = userService.registerBuyer(userDTO);
            return new ResponseEntity<>(newBuyer, HttpStatus.OK);
        } catch (UserException userException){
            return new ResponseEntity<>(new ApiResponse(false, userException.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
