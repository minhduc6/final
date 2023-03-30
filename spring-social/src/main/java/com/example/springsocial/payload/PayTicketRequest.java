package com.example.springsocial.payload;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
public class PayTicketRequest {

    private Long id;
    private String name;
    private String description;
    private float price;
    private Integer quantity;
    private Integer status;
    private Integer soLuong;
}
