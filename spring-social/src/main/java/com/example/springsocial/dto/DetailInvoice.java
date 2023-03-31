package com.example.springsocial.dto;

import com.example.springsocial.model.TypeTicket;
import lombok.Data;

@Data
public class DetailInvoice {

    private String NameTicket;

    private Float price;
    private Long quantity;

    public DetailInvoice(String nameTicket, Float price, Long quantity) {
        NameTicket = nameTicket;
        this.price = price;
        this.quantity = quantity;
    }
}
