package com.example.springsocial.dto;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceDTO {
    private List<DetailInvoice> detailInvoices;

    private  Float amount;

    public InvoiceDTO(List<DetailInvoice> detailInvoices, Float amount) {
        this.detailInvoices = detailInvoices;
        amount = amount;
    }
}
