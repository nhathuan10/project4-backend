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
}
