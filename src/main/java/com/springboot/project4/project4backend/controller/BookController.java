package com.springboot.project4.project4backend.controller;

import com.springboot.project4.project4backend.dto.BookDto;
import com.springboot.project4.project4backend.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin("*")
public class BookController {
    private BookService bookService;

    @PostMapping("/categories/{categoryId}/books")
    public ResponseEntity<BookDto> createBook(@PathVariable("categoryId") long categoryId, @RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.createBook(categoryId, bookDto), HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}/books")
    public ResponseEntity<List<BookDto>> getBooksByCategoryId(@PathVariable("categoryId") long categoryId){
        return new ResponseEntity<>(bookService.getBooksByCategoryId(categoryId), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") long id){
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @PutMapping("categories/{categoryId}/books/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("categoryId") long categoryId, @PathVariable("id") long id, @RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.updateBook(categoryId, id, bookDto), HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
    }
}
