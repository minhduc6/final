package com.example.springsocial.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class UpdateUser {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;

    private String roleID;
}
