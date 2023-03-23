package com.example.springsocial.dto;

import com.example.springsocial.model.Category;
import com.example.springsocial.model.Event;
import com.example.springsocial.payload.TypeTicketRequest;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class EventDto {
    private Long id;
    private String name;
    private String description;

    private String day;
    private String time;
    private String address;
    private Category category;
    private Integer status;

    private List<Category> categoryList;

    private List<TypeTicketRequest> typeTickets;
}
