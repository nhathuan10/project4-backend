package com.springboot.project4.project4backend.service.impl;

import com.springboot.project4.project4backend.dto.BookDto;
import com.springboot.project4.project4backend.dto.BookResponse;
import com.springboot.project4.project4backend.entity.Book;
import com.springboot.project4.project4backend.entity.Category;
import com.springboot.project4.project4backend.exception.ResourceNotFoundException;
import com.springboot.project4.project4backend.mapper.BookMapper;
import com.springboot.project4.project4backend.repository.BookRepository;
import com.springboot.project4.project4backend.repository.CategoryRepository;
import com.springboot.project4.project4backend.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public BookResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Book> books = bookRepository.findAll(pageable);
        List<Book> booksList = books.getContent();
        List<BookDto> content =  booksList.stream().map(BookMapper::mapToDto).collect(Collectors.toList());
        return new BookResponse(content, books.getNumber(), books.getSize(), books.getTotalElements(), books.getTotalPages(), books.isLast());
    }

    @Override
    public BookResponse getBooksByCategoryId(long categoryId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Book> books = bookRepository.findByCategoryId(categoryId, pageable);
        List<Book> booksList = books.getContent();
        List<BookDto> content =  booksList.stream().map(BookMapper::mapToDto).collect(Collectors.toList());
        return new BookResponse(content, books.getNumber(), books.getSize(), books.getTotalElements(), books.getTotalPages(), books.isLast());
    }

    @Override
    public BookDto getBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        return BookMapper.mapToDto(book);
    }

    @Override
    public BookDto updateBook(long categoryId, long id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setDescription(bookDto.getDescription());
        book.setCopies(bookDto.getCopies());
        book.setCopiesAvailable(bookDto.getCopies());
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        book.setCategory(category);
        book.setImg(bookDto.getImg());
        Book savedBook = bookRepository.save(book);
        return BookMapper.mapToDto(savedBook);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookResponse findBookByTitle(String title, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Book> books = bookRepository.findByTitleContaining(title, pageable);
        List<Book> booksList = books.getContent();
        List<BookDto> content =  booksList.stream().map(BookMapper::mapToDto).collect(Collectors.toList());
        return new BookResponse(content, books.getNumber(), books.getSize(), books.getTotalElements(), books.getTotalPages(), books.isLast());
    }
}
