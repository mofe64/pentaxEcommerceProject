package com.pentax.ecommerce.controllers;

import com.pentax.ecommerce.dtos.ApiResponse;
import com.pentax.ecommerce.exceptions.UserRoleNotFoundException;
import com.pentax.ecommerce.models.Role;
import com.pentax.ecommerce.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/new")
    public ResponseEntity<?> newRole(@RequestBody Role role){
        roleService.createNewRole(role);
        return new ResponseEntity<>(new ApiResponse(true, "Role created successfully"), HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getRoles(){
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    @GetMapping("{roleName}")
    public ResponseEntity<?> getRoleByName(@PathVariable String roleName){
        try {
            Role role = roleService.findByName(roleName);
            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (UserRoleNotFoundException userRoleNotFoundException){
            return new ResponseEntity<>(new ApiResponse(false, "No Role found by that name"), HttpStatus.BAD_REQUEST);
        }
    }

}
