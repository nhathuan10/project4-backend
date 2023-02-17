package com.springboot.project4.project4backend.repository;

import com.springboot.project4.project4backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategoryId(long categoryId);
}
