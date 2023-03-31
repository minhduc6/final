package com.example.springsocial.dto;

import lombok.Data;

@Data
public class StatisticalTicket {
    private String name;

    private Long sumTicket;

    public StatisticalTicket(String name, Long sumTicket) {
        this.name = name;
        this.sumTicket = sumTicket;
    }
}
