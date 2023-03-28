package com.springboot.project4.project4backend.controller;

import com.springboot.project4.project4backend.dto.ReviewDto;
import com.springboot.project4.project4backend.dto.ReviewResponse;
import com.springboot.project4.project4backend.security.JwtAuthenticationFilter;
import com.springboot.project4.project4backend.security.JwtTokenProvider;
import com.springboot.project4.project4backend.service.BookService;
import com.springboot.project4.project4backend.service.ReviewService;
import com.springboot.project4.project4backend.utils.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class ReviewController {
    private ReviewService reviewService;
    private JwtTokenProvider jwtTokenProvider;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @PostMapping("/books/{bookId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable("bookId") long bookId, @RequestBody ReviewDto reviewDto, HttpServletRequest request){
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        String userEmail = jwtTokenProvider.getUsernameFromToken(token);
        return new ResponseEntity<>(reviewService.createReview(userEmail, bookId, reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/books/{bookId}/reviews/checkIfReviewCreated")
    public ResponseEntity<Boolean> isReviewCreatedByUser(HttpServletRequest request, @PathVariable("bookId") long bookId){
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        String userEmail = jwtTokenProvider.getUsernameFromToken(token);
        return new ResponseEntity<>(reviewService.isReviewCreatedByUser(userEmail, bookId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reviews")
    public ResponseEntity<ReviewResponse> getAllReviews(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "8", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return ResponseEntity.ok(reviewService.getAllReviews(pageNo, pageSize, sortBy, sortDir));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") long id){
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review deleted successfully");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reviews/getReviewsByUserEmail")
    public ResponseEntity<List<ReviewDto>> getReviewsByUserEmail(@RequestParam("userEmail") String userEmail){
        return ResponseEntity.ok(reviewService.getReviewsByUserEmail(userEmail));
    }
}
