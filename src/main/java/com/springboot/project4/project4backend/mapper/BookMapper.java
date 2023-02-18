package com.springboot.project4.project4backend.mapper;

import com.springboot.project4.project4backend.dto.BookDto;
import com.springboot.project4.project4backend.entity.Book;

public class BookMapper {
    public static BookDto mapToDto(Book book){
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .copies(book.getCopies())
                .copiesAvailable(book.getCopiesAvailable())
                .img(book.getImg())
                .categoryName(book.getCategory().getName())
                .build();
    }

    public static Book mapToEntity(BookDto bookDto){
        return Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .description(bookDto.getDescription())
                .copies(bookDto.getCopies())
                .copiesAvailable(bookDto.getCopiesAvailable())
                .img(bookDto.getImg())
                .build();
    }
}
