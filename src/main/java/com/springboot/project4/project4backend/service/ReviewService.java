package com.springboot.project4.project4backend.service;

import com.springboot.project4.project4backend.dto.ReviewDto;

public interface ReviewService {
    ReviewDto createReview(String userEmail, long bookId, ReviewDto reviewDto);
    Boolean isReviewCreatedByUser(String userEmail, long bookId);
}
