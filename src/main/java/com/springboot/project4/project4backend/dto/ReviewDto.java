package com.springboot.project4.project4backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private long id;

    private String userEmail;

    private Date date;

    private double rating;

    private String description;

    private long bookId;
}
