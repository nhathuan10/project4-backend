package com.springboot.project4.project4backend.service;

import com.springboot.project4.project4backend.dto.BookDto;
import com.springboot.project4.project4backend.dto.BookResponse;
import com.springboot.project4.project4backend.dto.ShelfCurrentLoansResponse;

import java.text.ParseException;
import java.util.List;

public interface BookService {
    BookDto createBook(long categoryId, BookDto bookDto);
    BookResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir);
    BookResponse getBooksByCategoryId(long categoryId, int pageNo, int pageSize, String sortBy, String sortDir);
    BookDto getBookById(long id);
    BookDto updateBook(long categoryId, long id, BookDto bookDto);
    void deleteBook(long id);
    BookResponse findBookByTitle(String title, int pageNo, int pageSize, String sortBy, String sortDir);
    BookDto checkoutBook(String userEmail, long bookId);
    boolean checkoutBookByUser(String userEmail, long bookId);
    int currentLoansCount(String userEmail);
    List<ShelfCurrentLoansResponse> currentLoans(String userEmail) throws ParseException;
    void returnBook(String userEmail, long bookId);
    void renewLoan(String userEmail, long bookId) throws ParseException;
}
