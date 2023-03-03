package com.springboot.project4.project4backend.mapper;

import com.springboot.project4.project4backend.dto.CategoryDto;
import com.springboot.project4.project4backend.dto.CheckoutDto;
import com.springboot.project4.project4backend.entity.Category;
import com.springboot.project4.project4backend.entity.Checkout;

import java.util.stream.Collectors;

public class CheckoutMapper {
    public static CheckoutDto mapToDto(Checkout checkout){
        return CheckoutDto.builder()
                .id(checkout.getId())
                .userEmail(checkout.getUserEmail())
                .checkoutDate(checkout.getCheckOutDate())
                .returnDate(checkout.getReturnDate())
                .bookId(checkout.getBook().getId())
                .build();
    }

    public static Checkout mapToEntity(CheckoutDto checkoutDto){
        return Checkout.builder()
                .id(checkoutDto.getId())
                .userEmail(checkoutDto.getUserEmail())
                .checkOutDate(checkoutDto.getCheckoutDate())
                .returnDate(checkoutDto.getReturnDate())
                .build();
    }
}
