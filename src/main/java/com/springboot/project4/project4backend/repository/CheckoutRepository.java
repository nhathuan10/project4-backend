package com.springboot.project4.project4backend.repository;

import com.springboot.project4.project4backend.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    Checkout findByUserEmailAndBookId(String userEmail, long bookId);
}
