package com.example.springsocial.dto;

import com.example.springsocial.model.Invoice;
import com.example.springsocial.model.InvoiceDetailId;
import com.example.springsocial.model.TypeTicket;
import lombok.Data;

@Data
public class InvoiceDetailDto {
    private InvoiceDetailId id;

    private TypeTicketDto typeTicket;

    private Integer quantity;
}
