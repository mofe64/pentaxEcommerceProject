package com.pentax.ecommerce.services;

import com.pentax.ecommerce.dtos.ReviewDTO;
import com.pentax.ecommerce.exceptions.ReviewException;
import com.pentax.ecommerce.models.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    ReviewDTO findById(String Id) throws ReviewException;
    List<ReviewDTO> getAllReviews();
    void createNewReview(ReviewDTO review);
    List<ReviewDTO> getAllReviewsForAProduct(String productId);
    void deleteReview(String reviewId);
}
