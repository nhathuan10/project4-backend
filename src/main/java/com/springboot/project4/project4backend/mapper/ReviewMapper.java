package com.springboot.project4.project4backend.mapper;


import com.springboot.project4.project4backend.dto.ReviewDto;
import com.springboot.project4.project4backend.entity.Review;

import java.util.stream.Collectors;

public class ReviewMapper {
    public static ReviewDto mapToDto(Review review){
        return ReviewDto.builder()
                .id(review.getId())
                .userEmail(review.getUserEmail())
                .date(review.getDate())
                .rating(review.getRating())
                .description(review.getDescription())
                .bookTitle(review.getBook().getTitle())
                .build();
    }

    public static Review mapToEntity(ReviewDto reviewDto){
        return Review.builder()
                .id(reviewDto.getId())
                .userEmail(reviewDto.getUserEmail())
                .date(reviewDto.getDate())
                .rating(reviewDto.getRating())
                .description(reviewDto.getDescription())
                .build();
    }
}
