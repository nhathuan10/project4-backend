package com.springboot.project4.project4backend.mapper;

import com.springboot.project4.project4backend.dto.CategoryDto;
import com.springboot.project4.project4backend.entity.Category;

import java.util.stream.Collectors;

public class CategoryMapper {
    public static CategoryDto mapToDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .books(category.getBooks().stream().map(BookMapper::mapToDto).collect(Collectors.toList()))
                .build();
    }

    public static Category mapToEntity(CategoryDto categoryDto){
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .books(categoryDto.getBooks().stream().map(BookMapper::mapToEntity).collect(Collectors.toList()))
                .build();
    }
}
