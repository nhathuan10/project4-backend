package com.springboot.project4.project4backend.controller;

import com.springboot.project4.project4backend.dto.BookDto;
import com.springboot.project4.project4backend.dto.BookResponse;
import com.springboot.project4.project4backend.service.BookService;
import com.springboot.project4.project4backend.utils.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin("*")
public class BookController {
    private BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/categories/{categoryId}/books")
    public ResponseEntity<BookDto> createBook(@PathVariable("categoryId") long categoryId, @Valid @RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.createBook(categoryId, bookDto), HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public ResponseEntity<BookResponse> getAllBooks(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return new ResponseEntity<>(bookService.getAllBooks(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}/books")
    public ResponseEntity<BookResponse> getBooksByCategoryId(
            @PathVariable("categoryId") long categoryId,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return new ResponseEntity<>(bookService.getBooksByCategoryId(categoryId, pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") long id){
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("categories/{categoryId}/books/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("categoryId") long categoryId, @PathVariable("id") long id, @Valid @RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.updateBook(categoryId, id, bookDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/books/find-by-title")
    public ResponseEntity<BookResponse> getBookByTitle(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return new ResponseEntity<>(bookService.findBookByTitle(title, pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }
}
