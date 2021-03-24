package com.pentax.ecommerce.repository;

import com.pentax.ecommerce.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findRoleByName(String name);
}
