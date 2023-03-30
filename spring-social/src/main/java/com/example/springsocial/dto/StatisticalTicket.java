package com.example.springsocial.dto;

import lombok.Data;

@Data
public class StatisticalTicket {
    private Long id;

    private Integer sumTicket;

    public StatisticalTicket(Long id, Integer sumTicket) {
        this.id = id;
        this.sumTicket = sumTicket;
    }
}
