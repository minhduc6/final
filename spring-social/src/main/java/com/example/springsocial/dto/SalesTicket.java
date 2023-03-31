package com.example.springsocial.dto;

import lombok.Data;

@Data
public class SalesTicket {

    private Long id;

    private Long sumTicket;

    private Float price;

    private Float sumPrice;

    public SalesTicket(Long id, Long sumTicket, Float price, Float sumPrice) {
        this.id = id;
        this.sumTicket = sumTicket;
        this.price = price;
        this.sumPrice = sumPrice;
    }
}
