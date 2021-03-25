package com.pentax.ecommerce.dtos;

import com.pentax.ecommerce.models.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private String Id;
    private String productId;
    private String review;

    public static Review unpackDto(ReviewDTO reviewDTO){
        Review review = new Review();
        review.setProductId(reviewDTO.getProductId());
        review.setReview(reviewDTO.getReview());
        return review;
    }
    public static ReviewDTO packDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setProductId(review.getProductId());
        reviewDTO.setReview(review.getReview());
        return  reviewDTO;
    }
}
