package com.example.springsocial.controller;

import com.example.springsocial.model.Invoice;
import com.example.springsocial.payload.PayRequest;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private IPaymentService iPaymentService;
    @PostMapping ("/payment/")
    @PreAuthorize("hasRole('USER')")
    public Invoice paymentInvoice(@CurrentUser UserPrincipal userPrincipal, @RequestBody PayRequest payRequest) {
        System.out.println("123" + payRequest.getPayTicketRequestList().get(0).getSoLuong());
        return iPaymentService.payInvoice(userPrincipal,payRequest);
    }
}
