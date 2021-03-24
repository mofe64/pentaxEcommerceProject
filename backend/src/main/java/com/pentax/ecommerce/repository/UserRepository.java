package com.pentax.ecommerce.repository;

import com.pentax.ecommerce.models.Address;
import com.pentax.ecommerce.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findUserByEmail(String email);
    List<User> findUsersByFirstname(String firstname);
    List<User> findUserByLastname(String lastname);
    Optional<User> findUserById(String id);
    void deleteUserById(String id);

}
