package com.springboot.project4.project4backend.repository;

import com.springboot.project4.project4backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByUserEmailAndBookId(String userEmail, long bookId);
    List<Review> findByUserEmailContaining(String userEmail);
}
