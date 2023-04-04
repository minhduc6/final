package com.example.springsocial.controller;

import com.example.springsocial.dto.MyTicketDTO;
import com.example.springsocial.model.Invoice;
import com.example.springsocial.payload.PayRequest;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.IPaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private IPaymentService iPaymentService;
    @PostMapping (value = "/payment" , consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @PreAuthorize("hasRole('USER')")
    public Invoice paymentInvoice(@CurrentUser UserPrincipal userPrincipal, @RequestPart("payRequest") String payRequest) {
        PayRequest payRequest1 = new PayRequest();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            payRequest1 = objectMapper.readValue(payRequest,PayRequest.class);
        }catch (IOException err){
            System.out.println("ERR " + err);
        }
        System.out.println("Payment Request String :" + payRequest);
        System.out.println("Payment Request :" + payRequest1.getPayTicketRequestList().size());
        return iPaymentService.payInvoice(userPrincipal,payRequest1);
    }

    @GetMapping("/myInvoice")
    @PreAuthorize("hasRole('USER')")
    public List<Invoice> getMyInvoice(@CurrentUser UserPrincipal userPrincipal) {
        return iPaymentService.findInvoiceByUser(userPrincipal);
    }

    @GetMapping("/myInvoice/detail/{id}")
    @PreAuthorize("hasRole('USER')")
    public List<MyTicketDTO> getMyInvoiceDetail(@PathVariable Long id) {
        return iPaymentService.findByTicketByInvoice(id);
    }
}
