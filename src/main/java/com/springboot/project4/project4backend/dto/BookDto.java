package com.springboot.project4.project4backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private long id;
    private String title;
    private String author;
    private String description;
    private int copies;
    private int copiesAvailable;
    private String img;
    private long categoryId;
}