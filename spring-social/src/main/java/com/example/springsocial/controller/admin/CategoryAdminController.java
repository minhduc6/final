package com.example.springsocial.controller.admin;


import com.example.springsocial.model.Category;
import com.example.springsocial.payload.CategoryRequest;
import com.example.springsocial.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class CategoryAdminController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/category")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Category> getAllCategory() {
        return categoryService.getAll();
    }

    @GetMapping("/categoryById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Category getCategoryById(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id).get();
    }

    @PostMapping(value = "/category")
    @PreAuthorize("hasRole('ADMIN')")
    public Category createCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @PutMapping(value = "/category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Category> updateCategory(@PathVariable("id")  Long id, @RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateCategory(id,categoryRequest);
    }

    @DeleteMapping("/category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Category deleteCategory(@PathVariable("id") Long id) {
        return categoryService.deleteCategory(id);
    }




}
