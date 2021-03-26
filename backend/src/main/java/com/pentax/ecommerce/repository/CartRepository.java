package com.pentax.ecommerce.repository;

import com.pentax.ecommerce.models.Cart;
import com.pentax.ecommerce.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    Optional<Cart> findCartById(String Id);

}
