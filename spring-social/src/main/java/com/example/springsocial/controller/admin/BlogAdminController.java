package com.example.springsocial.controller.admin;

import com.example.springsocial.model.Blog;
import com.example.springsocial.payload.BlogRequest;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.IBlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class BlogAdminController {

    @Autowired
    private IBlogService blogService;

    @GetMapping("/blog")
    public List<Blog> getBlogs() {
        return blogService.getAll();
    }

    @PostMapping(value = "/blog", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasRole('ADMIN')")
    public Blog createBlog(@CurrentUser UserPrincipal userPrincipal, @RequestPart(value = "file", required = false) MultipartFile file, @RequestPart("blog") String blogRequest) {
        BlogRequest blogRequest1 = new BlogRequest();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            blogRequest1 = objectMapper.readValue(blogRequest, BlogRequest.class);
        } catch (IOException err) {
            System.out.println("ERR " + err);
        }
        return blogService.createBlog(userPrincipal, file, blogRequest1);
    }

    @PutMapping(value = "/blog/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasRole('ADMIN')")
    public Blog updateBlog(@PathVariable Long id, @CurrentUser UserPrincipal userPrincipal, @RequestPart(value = "file", required = false) MultipartFile file, @RequestPart("blog") String blogRequest) {
        BlogRequest blogRequest1 = new BlogRequest();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            blogRequest1 = objectMapper.readValue(blogRequest, BlogRequest.class);
        } catch (IOException err) {
            System.out.println("ERR " + err);
        }

        return blogService.updateBlog(id, userPrincipal, file, blogRequest1);
    }

    @GetMapping("/blog/{id}")
    public Blog getBlogByID(@PathVariable("id") Long id) {
        return blogService.getBlogById(id);
    }

    @DeleteMapping("/blog/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBlog(@PathVariable("id") Long id) {
        blogService.deleteBlog(id);
    }


}
