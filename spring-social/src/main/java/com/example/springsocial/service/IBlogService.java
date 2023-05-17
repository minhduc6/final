package com.example.springsocial.service;

import com.example.springsocial.dto.EventDto;
import com.example.springsocial.model.Blog;
import com.example.springsocial.model.Event;
import com.example.springsocial.payload.BlogRequest;
import com.example.springsocial.payload.EventRequest;
import com.example.springsocial.security.UserPrincipal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface IBlogService {
    Blog createBlog(UserPrincipal userPrincipal , MultipartFile multipartFile, BlogRequest blogRequest);

    Blog  updateBlog(Long id ,UserPrincipal userPrincipal , MultipartFile multipartFile, BlogRequest blogRequest);

    List<Blog> getAll();

    void deleteBlog(Long id);

    Blog getBlogById(Long id);
}
