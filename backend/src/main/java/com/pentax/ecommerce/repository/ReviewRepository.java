package com.pentax.ecommerce.repository;

import com.pentax.ecommerce.models.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository <Review, String> {
    Optional<Review> findById(String Id);
    List<Review> findAllByProductId(String productId);
}
