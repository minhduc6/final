package com.example.springsocial.service;

import com.example.springsocial.dto.InvoiceDetailDto;
import com.example.springsocial.model.Invoice;
import com.example.springsocial.model.InvoiceDetail;

import java.util.List;

public interface IInvoiceService {
    List<Invoice> findAllInvoice();

    List<InvoiceDetailDto> findByInvoiceId(Long id);

    List<Invoice> findFilterInvoice(String keyword , String form , String to);

    Invoice updateStatus(Long id,Integer status);
}
