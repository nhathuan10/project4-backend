package com.springboot.project4.project4backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelfCurrentLoansResponse {
    private BookDto book;
    private int daysLeft;
}
