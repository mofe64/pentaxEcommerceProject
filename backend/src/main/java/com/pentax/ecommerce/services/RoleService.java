package com.pentax.ecommerce.services;

import com.pentax.ecommerce.exceptions.UserRoleNotFoundException;
import com.pentax.ecommerce.models.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    Role findByName(String name) throws UserRoleNotFoundException;
    List<Role> getAllRoles();
    void createNewRole(Role role);
}
