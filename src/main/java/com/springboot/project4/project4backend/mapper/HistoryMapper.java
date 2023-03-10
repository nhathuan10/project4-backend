package com.springboot.project4.project4backend.mapper;

import com.springboot.project4.project4backend.dto.HistoryDto;
import com.springboot.project4.project4backend.entity.History;

public class HistoryMapper {
    public static HistoryDto mapToDto(History history){
        return HistoryDto.builder()
                .id(history.getId())
                .userEmail(history.getUserEmail())
                .checkoutDate(history.getCheckoutDate())
                .returnedDate(history.getReturnedDate())
                .title(history.getTitle())
                .author(history.getAuthor())
                .description(history.getDescription())
                .img(history.getImg())
                .verified(history.isVerified())
                .validated(history.getValidated())
                .bookId(history.getBookId())
                .build();
    }

    public static History mapToEntity(HistoryDto historyDto){
        return History.builder()
                .id(historyDto.getId())
                .userEmail(historyDto.getUserEmail())
                .checkoutDate(historyDto.getCheckoutDate())
                .returnedDate(historyDto.getReturnedDate())
                .title(historyDto.getTitle())
                .author(historyDto.getAuthor())
                .description(historyDto.getDescription())
                .img(historyDto.getImg())
                .verified(historyDto.isVerified())
                .build();
    }
}
