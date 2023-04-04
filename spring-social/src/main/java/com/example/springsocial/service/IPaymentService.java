package com.example.springsocial.service;

import com.example.springsocial.dto.MyTicketDTO;
import com.example.springsocial.model.Invoice;
import com.example.springsocial.payload.PayRequest;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;

import java.util.List;

public interface IPaymentService {
    Invoice payInvoice(@CurrentUser UserPrincipal userPrincipal , PayRequest payRequest);

    List<Invoice> findInvoiceByUser(@CurrentUser UserPrincipal userPrincipal);

    List<MyTicketDTO> findByTicketByInvoice(Long id);


}
