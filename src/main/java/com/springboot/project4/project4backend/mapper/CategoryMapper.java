package com.springboot.project4.project4backend.mapper;

import com.springboot.project4.project4backend.dto.CategoryDto;
import com.springboot.project4.project4backend.entity.Category;

public class CategoryMapper {
    public static CategoryDto mapToDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category mapToEntity(CategoryDto categoryDto){
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }
}
