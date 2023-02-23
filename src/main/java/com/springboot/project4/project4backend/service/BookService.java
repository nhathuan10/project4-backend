package com.springboot.project4.project4backend.service;

import com.springboot.project4.project4backend.dto.BookDto;
import com.springboot.project4.project4backend.dto.BookResponse;

import java.util.List;

public interface BookService {
    BookDto createBook(long categoryId, BookDto bookDto);
    BookResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir);
    List<BookDto> getBooksByCategoryId(long categoryId);
    BookDto getBookById(long id);
    BookDto updateBook(long categoryId, long id, BookDto bookDto);
    void deleteBook(long id);
}
