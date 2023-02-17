package com.springboot.project4.project4backend.service.impl;

import com.springboot.project4.project4backend.dto.BookDto;
import com.springboot.project4.project4backend.entity.Book;
import com.springboot.project4.project4backend.entity.Category;
import com.springboot.project4.project4backend.exception.ResourceNotFoundException;
import com.springboot.project4.project4backend.mapper.BookMapper;
import com.springboot.project4.project4backend.repository.BookRepository;
import com.springboot.project4.project4backend.repository.CategoryRepository;
import com.springboot.project4.project4backend.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private CategoryRepository categoryRepository;
    private BookRepository bookRepository;
    @Override
    public BookDto createBook(long categoryId, BookDto bookDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", categoryId));
        Book book = BookMapper.mapToEntity(bookDto);
        book.setCategory(category);
        Book savedBook = bookRepository.save(book);
        return BookMapper.mapToDto(savedBook);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksByCategoryId(long categoryId) {
        List<Book> books = bookRepository.findByCategoryId(categoryId);
        return books.stream().map(BookMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        return BookMapper.mapToDto(book);
    }

    @Override
    public BookDto updateBook(long id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setDescription(bookDto.getDescription());
        book.setCopies(bookDto.getCopies());
        book.setCopiesAvailable(bookDto.getCopiesAvailable());
        Category category = categoryRepository.findById(bookDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", bookDto.getCategoryId()));
        book.setCategory(category);
        book.setImg(bookDto.getImg());
        Book savedBook = bookRepository.save(book);
        return BookMapper.mapToDto(savedBook);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
