package com.example.springsocial.controller;


import com.example.springsocial.dto.EventDto;
import com.example.springsocial.model.Blog;
import com.example.springsocial.model.Event;
import com.example.springsocial.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @GetMapping("/blog")
    public List<Blog> getListBlog() {
        return blogService.getAll();
    }
    @GetMapping("/blog/{id}")
    public Blog getBlogByID(@PathVariable("id") Long id) {
        return  blogService.getBlogById(id);
    }
}
