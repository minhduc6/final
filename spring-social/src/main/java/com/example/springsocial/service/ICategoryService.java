package com.example.springsocial.service;

import com.example.springsocial.model.Category;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.CategoryRequest;
import com.example.springsocial.payload.SignUpRequest;
import com.example.springsocial.payload.UpdateUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> getAll();

    Optional<Category> getCategoryById(Long id);

    Category createCategory(CategoryRequest categoryRequest);

    Optional<Category> updateCategory(Long id , CategoryRequest categoryRequest);

    Category deleteCategory(Long id);

}
