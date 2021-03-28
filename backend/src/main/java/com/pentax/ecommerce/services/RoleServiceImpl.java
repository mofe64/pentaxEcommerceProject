package com.pentax.ecommerce.services;

import com.pentax.ecommerce.exceptions.UserRoleNotFoundException;
import com.pentax.ecommerce.models.Role;
import com.pentax.ecommerce.repository.RoleRepository;
import com.pentax.ecommerce.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findByName(String name) throws UserRoleNotFoundException {
        return findRoleByName(name);
    }

    @Override
    public List<Role> getAllRoles() {
        return getAllRolesInDb();
    }
    private List<Role> getAllRolesInDb(){
       return roleRepository.findAll();
    }

    private Role findRoleByName(String name) throws UserRoleNotFoundException{
        Optional<Role> roleOptional = roleRepository.findRoleByName(name);
        if(roleOptional.isPresent()){
            return roleOptional.get();
        } else {
            throw new UserRoleNotFoundException();
        }
    }

    @Override
    public void createNewRole(Role role) {
        createANewRole(role);
    }

    private void createANewRole(Role role){
        roleRepository.save(role);
    }
}
