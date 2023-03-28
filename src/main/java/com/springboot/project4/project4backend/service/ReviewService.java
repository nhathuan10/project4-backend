package com.springboot.project4.project4backend.service;

import com.springboot.project4.project4backend.dto.ReviewDto;
import com.springboot.project4.project4backend.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(String userEmail, long bookId, ReviewDto reviewDto);
    Boolean isReviewCreatedByUser(String userEmail, long bookId);
    ReviewResponse getAllReviews(int pageNo, int pageSize, String sortBy, String sortDir);
    void deleteReview(long id);
    List<ReviewDto> getReviewsByUserEmail(String userEmail);
}
