package com.springboot.project4.project4backend.service.impl;

import com.springboot.project4.project4backend.dto.BookResponse;
import com.springboot.project4.project4backend.dto.ReviewDto;
import com.springboot.project4.project4backend.dto.ReviewResponse;
import com.springboot.project4.project4backend.entity.Book;
import com.springboot.project4.project4backend.entity.Review;
import com.springboot.project4.project4backend.exception.APIException;
import com.springboot.project4.project4backend.exception.ResourceNotFoundException;
import com.springboot.project4.project4backend.mapper.BookMapper;
import com.springboot.project4.project4backend.mapper.ReviewMapper;
import com.springboot.project4.project4backend.repository.BookRepository;
import com.springboot.project4.project4backend.repository.ReviewRepository;
import com.springboot.project4.project4backend.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private BookRepository bookRepository;

    @Override
    public ReviewDto createReview(String userEmail, long bookId, ReviewDto reviewDto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
        Optional<Review> validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);
        if (validateReview.isPresent()) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Review already created");
        }
        Review review = ReviewMapper.mapToEntity(reviewDto);
        review.setUserEmail(userEmail);
        review.setBook(book);
        Review savedReview = reviewRepository.save(review);
        return ReviewMapper.mapToDto(savedReview);
    }

    @Override
    public Boolean isReviewCreatedByUser(String userEmail, long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
        Optional<Review> validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);
        return validateReview.isPresent();
    }

    @Override
    public ReviewResponse getAllReviews(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Review> reviews = reviewRepository.findAll(pageable);
        List<Review> reviewList = reviews.getContent();
        List<ReviewDto> content =  reviewList.stream().map(ReviewMapper::mapToDto).collect(Collectors.toList());
        return new ReviewResponse(content, reviews.getNumber(), reviews.getSize(), reviews.getTotalElements(), reviews.getTotalPages(), reviews.isLast());
    }

    @Override
    public void deleteReview(long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review", "id", id));
        reviewRepository.deleteById(id);
    }

    @Override
    public List<ReviewDto> getReviewsByUserEmail(String userEmail) {
        return null;
    }
}
