package com.pentax.ecommerce.services;

import com.pentax.ecommerce.dtos.ReviewDTO;
import com.pentax.ecommerce.exceptions.ReviewException;
import com.pentax.ecommerce.models.Review;
import com.pentax.ecommerce.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public ReviewDTO findById(String reviewId) throws ReviewException {
        Review foundReview = findAReviewById(reviewId);
        ReviewDTO reviewDTO = ReviewDTO.packDTO(foundReview);
        return reviewDTO;
    }
    private Review findAReviewById(String id) throws ReviewException {
        Optional<Review> reviewOptional =reviewRepository.findById(id);
        if(reviewOptional.isPresent()){
            return reviewOptional.get();
        } else {
            throw new ReviewException("No Review found with that Id");
        }
    }


    @Override
    public List<ReviewDTO> getAllReviews() {
        return null;
    }

    @Override
    public void createNewReview(ReviewDTO review) {

    }

    @Override
    public List<ReviewDTO> getAllReviewsForAProduct(String productId) {
        return null;
    }

    @Override
    public void deleteReview(String reviewId) {

    }
}
