package com.pentax.ecommerce.repository;

import com.pentax.ecommerce.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findProductByPriceGreaterThan(BigDecimal price);
    Optional<Product> findProductById(String id);
    //Todo add by category
    List<Product> findProductByDescriptionContaining(String phrase);
}
