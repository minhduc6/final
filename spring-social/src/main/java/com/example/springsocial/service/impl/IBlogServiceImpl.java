package com.example.springsocial.service.impl;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.Blog;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.BlogRequest;
import com.example.springsocial.repository.BlogRepository;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.IBlogService;
import com.example.springsocial.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IBlogServiceImpl implements IBlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Blog createBlog(UserPrincipal userPrincipal, MultipartFile multipartFile, BlogRequest blogRequest) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        Blog blog = new Blog();
        blog.setName(blogRequest.getName());
        blog.setDescription(blogRequest.getDescription());
        blog.setContent(blogRequest.getContent());
        blog.setCreated_time(LocalDateTime.now());
        if(multipartFile != null){
            blog.setImage(FileUtil.uploadFile(multipartFile));
        }
        blog.setUser(user);
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public Blog updateBlog(Long id, UserPrincipal userPrincipal, MultipartFile multipartFile, BlogRequest blogRequest) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog", "id", id));
        blog.setName(blogRequest.getName());
        blog.setDescription(blogRequest.getDescription());
        blog.setContent(blogRequest.getContent());
        blog.setUpdated_time(LocalDateTime.now());
        if(multipartFile != null){
            blog.setImage(FileUtil.uploadFile(multipartFile));
        }
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog", "id", id));
    }
}
