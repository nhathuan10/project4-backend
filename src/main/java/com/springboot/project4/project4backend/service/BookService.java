package com.springboot.project4.project4backend.service;

import com.springboot.project4.project4backend.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(long categoryId, BookDto bookDto);
    List<BookDto> getAllBooks();
}
