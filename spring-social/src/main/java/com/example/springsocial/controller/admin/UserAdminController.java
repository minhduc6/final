package com.example.springsocial.controller.admin;

import com.example.springsocial.exception.BadRequestException;
import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.SignUpRequest;
import com.example.springsocial.payload.UpdateUser;
import com.example.springsocial.service.IUserService;
import com.example.springsocial.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class UserAdminController {
    @Autowired
    private IUserService userService;

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUser() {
        return userService.getAll();
    }

    @GetMapping("/userById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id).get();
    }

    @PostMapping(value = "/user" ,consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(@RequestPart(value = "file") MultipartFile file,@ModelAttribute SignUpRequest signUpRequest) {
        System.out.println("SIGN REQ" + signUpRequest);
        return userService.createUser(signUpRequest,file);
    }


    @PutMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<User> updateUser(@PathVariable("id") Long id,@RequestPart(value = "file" ,required = false) MultipartFile file, @ModelAttribute UpdateUser updateUser) {
        return userService.updateUser(id, updateUser,file);
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<User> deleteUser(@PathVariable("id") Long id) {
        return Optional.ofNullable(userService.deleteUser(id).orElseThrow(() -> new BadRequestException("User còn  nhiều dữ liệu")));
    }
}
