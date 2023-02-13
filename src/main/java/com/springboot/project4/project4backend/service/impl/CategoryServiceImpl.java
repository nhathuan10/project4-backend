package com.springboot.project4.project4backend.service.impl;

import com.springboot.project4.project4backend.dto.CategoryDto;
import com.springboot.project4.project4backend.entity.Category;
import com.springboot.project4.project4backend.exception.ResourceNotFoundException;
import com.springboot.project4.project4backend.mapper.CategoryMapper;
import com.springboot.project4.project4backend.repository.CategoryRepository;
import com.springboot.project4.project4backend.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.mapToEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToDto(savedCategory);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return CategoryMapper.mapToDto(category);
    }

    @Override
    public CategoryDto updateCategory(long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        category.setName(categoryDto.getName());
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToDto(savedCategory);
    }

    @Override
    public void deleteCategory(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);
    }
}
