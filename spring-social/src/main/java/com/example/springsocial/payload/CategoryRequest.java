package com.example.springsocial.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class CategoryRequest {

    private String name;

    private String description;
}
