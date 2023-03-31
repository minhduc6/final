package com.example.springsocial.dto;


import lombok.Data;

@Data
public class MyTicketDTO {
    private String typeTicket;
    private String serialCode;
    private Float price;


    public MyTicketDTO(String typeTicket, String serialCode, Float price) {
        this.typeTicket = typeTicket;
        this.serialCode = serialCode;
        this.price = price;
    }
}
