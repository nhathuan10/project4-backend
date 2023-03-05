package com.springboot.project4.project4backend.repository;

import com.springboot.project4.project4backend.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUserEmail(String userEmail);
}
