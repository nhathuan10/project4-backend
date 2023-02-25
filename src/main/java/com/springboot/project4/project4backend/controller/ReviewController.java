package com.springboot.project4.project4backend.controller;

import com.springboot.project4.project4backend.dto.ReviewDto;
import com.springboot.project4.project4backend.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class ReviewController {
    private ReviewService reviewService;

    @PostMapping("/books/{bookId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable("bookId") long bookId, @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(bookId, reviewDto), HttpStatus.CREATED);
    }
}
