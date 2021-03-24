package com.pentax.ecommerce.controllers;

import com.pentax.ecommerce.dtos.UserDTO;
import com.pentax.ecommerce.services.UserService;
import com.pentax.ecommerce.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register/buyer")
    public ResponseEntity<?> registerBuyer( @Valid @RequestBody UserDTO userDTO){
        System.out.println(userDTO);
       UserDTO response = userService.registerBuyer(userDTO);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }


//    @PostMapping("/register/seller")
//    public ResponseEntity<?> registerSeller(){
//
//    }
}
