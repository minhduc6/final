package com.example.springsocial.service;

import com.example.springsocial.model.Invoice;
import com.example.springsocial.payload.PayRequest;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;

public interface IPaymentService {
    Invoice payInvoice(@CurrentUser UserPrincipal userPrincipal , PayRequest payRequest);
}
