package com.springboot.project4.project4backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckoutDto {
    private long id;
    private String userEmail;
    private String checkoutDate;
    private String returnDate;
    private long bookId;
}
