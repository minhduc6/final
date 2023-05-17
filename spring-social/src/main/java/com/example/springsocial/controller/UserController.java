package com.example.springsocial.controller;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.UpdateUser;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @PutMapping("/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public Optional<User> updateUser(@PathVariable("id") Long id, @RequestPart(value = "file" ,required = false) MultipartFile file, @ModelAttribute UpdateUser updateUser) {
        return userService.updateUser(id, updateUser,file);
    }



}
