package com.springboot.project4.project4backend.repository;

import com.springboot.project4.project4backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
