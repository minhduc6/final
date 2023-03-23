package com.example.springsocial.payload;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventRequest {

    private String name;

    private String description;

    private MultipartFile file;

    private String day;

    private String time;

    private String address;
    private Integer status;
    private LocalDateTime create_at;
    private LocalDateTime update_at;

    private List<Long> listCategory;

    private List<TypeTicketRequest> ticketList;
}
