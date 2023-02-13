package com.springboot.project4.project4backend.repository;

import com.springboot.project4.project4backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
