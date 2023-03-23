package com.example.springsocial.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class SignUpRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;


    private List<String> roleID;

    public List<String> getRoleID() {
        return roleID;
    }

    public void setRoleID(List<String> roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
