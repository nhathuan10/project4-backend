package com.springboot.project4.project4backend.repository;

import com.springboot.project4.project4backend.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByCategoryId(long categoryId, Pageable pageable);
    Page<Book> findByTitleContaining(String title, Pageable pageable);
    @Query("select b from Book b where b.id in :book_ids")
    List<Book> findBooksByBookIds(@Param("book_ids") List<Long> bookIds);
}
