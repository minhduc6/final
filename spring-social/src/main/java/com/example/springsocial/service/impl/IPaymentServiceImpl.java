package com.example.springsocial.service.impl;


import com.example.springsocial.exception.BadRequestException;
import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.*;
import com.example.springsocial.payload.PayRequest;
import com.example.springsocial.repository.InvoiceDetailRepository;
import com.example.springsocial.repository.InvoiceRepository;
import com.example.springsocial.repository.TypeTicketRepository;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class IPaymentServiceImpl implements IPaymentService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    private TypeTicketRepository typeTicketRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Invoice payInvoice(UserPrincipal userPrincipal, PayRequest payRequest) {
        User user = userRepository.findById(userPrincipal.getId()).get();
        Invoice invoice = Invoice.builder().userOrder(user)
                .nameRecv(payRequest.getNameRecv())
                .phoneRecv(payRequest.getPhoneRecv())
                .addressRecv(payRequest.getAddressRecv())
                .amount(payRequest.getAmount())
                .times(LocalDateTime.now())
                .status(0).build();
        invoiceRepository.save(invoice);

        for (int i = 0; i < payRequest.getPayTicketRequestList().size(); i++) {
            Long id = payRequest.getPayTicketRequestList().get(i).getId();
            TypeTicket typeTicket = typeTicketRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Ticket", "id", id));
            if (typeTicket.getQuantity() - payRequest.getPayTicketRequestList().get(i).getSoLuong()  < 0) {
                throw new BadRequestException("Không đủ Số Lượng Vé");
            }
            typeTicket.setQuantity(typeTicket.getQuantity() - payRequest.getPayTicketRequestList().get(i).getSoLuong());
            typeTicketRepository.save(typeTicket);
            InvoiceDetailId invoiceDetailId = InvoiceDetailId.builder().invoiceId(invoice.getId()).ticketId(typeTicket.getId()).build();
            InvoiceDetail invoiceDetail = InvoiceDetail.builder().id(invoiceDetailId).invoice(invoice).typeTicket(typeTicket).quantity(payRequest.getPayTicketRequestList().get(i).getSoLuong()).build();
            invoiceDetailRepository.save(invoiceDetail);
        }


        return invoice;
    }
}
