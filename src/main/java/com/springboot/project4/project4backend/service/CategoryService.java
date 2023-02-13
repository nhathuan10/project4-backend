package com.springboot.project4.project4backend.service;

import com.springboot.project4.project4backend.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(long id);
    CategoryDto updateCategory(long id, CategoryDto categoryDto);
    void deleteCategory(long id);
}
