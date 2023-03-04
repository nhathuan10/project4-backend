package com.springboot.project4.project4backend.controller;

import com.springboot.project4.project4backend.dto.ReviewDto;
import com.springboot.project4.project4backend.security.JwtAuthenticationFilter;
import com.springboot.project4.project4backend.security.JwtTokenProvider;
import com.springboot.project4.project4backend.service.BookService;
import com.springboot.project4.project4backend.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
}
