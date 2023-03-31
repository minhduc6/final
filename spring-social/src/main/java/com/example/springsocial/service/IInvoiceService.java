package com.example.springsocial.service;

import com.example.springsocial.dto.InvoiceDTO;
import com.example.springsocial.dto.InvoiceDetailDto;
import com.example.springsocial.model.Invoice;
import com.example.springsocial.model.InvoiceDetail;
import com.google.zxing.WriterException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface IInvoiceService {
    List<Invoice> findAllInvoice();

    InvoiceDTO findByInvoiceId(Long id);

    List<Invoice> findFilterInvoice(String keyword , String form , String to);

    void sendEmail(String email , Long id) throws MessagingException, IOException, WriterException;

    Invoice updateStatus(Long id,Integer status);
}
