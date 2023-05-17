package com.example.springsocial.payload;


import lombok.Data;

@Data
public class BlogRequest {
    private String name;
    private String description;
    private String content;
}
