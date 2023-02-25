package com.springboot.project4.project4backend.service.impl;

import com.springboot.project4.project4backend.dto.ReviewDto;
import com.springboot.project4.project4backend.entity.Book;
import com.springboot.project4.project4backend.entity.Review;
import com.springboot.project4.project4backend.exception.ResourceNotFoundException;
import com.springboot.project4.project4backend.mapper.ReviewMapper;
import com.springboot.project4.project4backend.repository.BookRepository;
import com.springboot.project4.project4backend.repository.ReviewRepository;
import com.springboot.project4.project4backend.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private BookRepository bookRepository;

    @Override
    public ReviewDto createReview(long bookId, ReviewDto reviewDto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
        Review review = ReviewMapper.mapToEntity(reviewDto);
        review.setBook(book);
        Review savedReview = reviewRepository.save(review);
        return ReviewMapper.mapToDto(savedReview);
    }
}
