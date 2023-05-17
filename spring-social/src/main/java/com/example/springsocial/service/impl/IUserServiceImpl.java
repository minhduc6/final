package com.example.springsocial.service.impl;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.Role;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.SignUpRequest;
import com.example.springsocial.payload.UpdateUser;
import com.example.springsocial.repository.RoleRepository;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.service.IUserService;
import com.example.springsocial.util.FileUtil;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(SignUpRequest signUpRequest, MultipartFile file) {
        User user = new User();
        HashSet<Role> roles = new HashSet<>();
        for (int i = 0; i < signUpRequest.getRoleID().size(); i++) {
             Role role = roleRepository.findById(Long.parseLong(signUpRequest.getRoleID().get(i))).get();
             roles.add(role);
        }
        if(file != null){
            user.setImgUrl(FileUtil.uploadFile(file));
        }
        user.setRoles(roles);
        user.setDisplayName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateUser(Long id, UpdateUser updateUser, MultipartFile file) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );
        String[] arrRole = updateUser.getRoleID().trim().split(",");
        HashSet<Role> roles = new HashSet<>();
        for (int i = 0; i < arrRole.length; i++) {
            Role role = roleRepository.findById(Long.parseLong(arrRole[i])).get();
            roles.add(role);
        }
        if(file != null){
            user.setImgUrl(FileUtil.uploadFile(file));
        }
        user.setRoles(roles);
        user.setDisplayName(updateUser.getName());
        if(updateUser.getPassword() != null && updateUser.getPassword().isEmpty() != true && updateUser.getPassword().isBlank() != true){
            user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        }
        user.setEmail(updateUser.getEmail());
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> deleteUser(Long id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        ));
        try {
             userRepository.deleteById(id);
        } catch (Exception exception) {
            throw new RuntimeException("User tồn tại nhiều dữ liệu không nên xoá");
        }
        return user;
    }
}
