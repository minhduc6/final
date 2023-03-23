package com.example.springsocial.service;

import com.example.springsocial.model.Event;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.SignUpRequest;
import com.example.springsocial.payload.UpdateUser;
import com.example.springsocial.service.criteria.EventSearchCriteria;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getAll();

    Optional<User> getUserById(Long id);

    User createUser(SignUpRequest signUpRequest, MultipartFile file);

    Optional<User> updateUser(Long id , UpdateUser updateUser,MultipartFile file);

    Optional<User> deleteUser(Long id);
}
