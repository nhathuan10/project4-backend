package com.springboot.project4.project4backend.service;

import com.springboot.project4.project4backend.dto.ReviewDto;

public interface ReviewService {
    ReviewDto createReview(long bookId, ReviewDto reviewDto);
}
