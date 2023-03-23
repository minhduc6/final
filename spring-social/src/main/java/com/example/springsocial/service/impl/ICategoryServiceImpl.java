package com.example.springsocial.service.impl;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.Category;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.CategoryRequest;
import com.example.springsocial.repository.CategoryRepository;
import com.example.springsocial.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ICategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Optional<Category> updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", id)
        );
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        return Optional.of(categoryRepository.save(category));
    }

    @Override
    public Category deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", id)
        );
        categoryRepository.deleteById(id);
        return category;
    }
}
