package com.example.springsocial.payload;


import lombok.Data;

import java.util.List;

@Data
public class PayRequest {
    private  Float amount;

    private String nameRecv;

    private String phoneRecv;

    private String addressRecv;

    List<PayTicketRequest> payTicketRequestList;
}
