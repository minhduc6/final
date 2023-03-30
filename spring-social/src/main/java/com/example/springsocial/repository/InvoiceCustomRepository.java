package com.example.springsocial.repository;

import com.example.springsocial.model.Invoice;

import java.util.List;

public interface InvoiceCustomRepository {
    List<Invoice> filterInvoice(String keyword, String from, String to);
}
