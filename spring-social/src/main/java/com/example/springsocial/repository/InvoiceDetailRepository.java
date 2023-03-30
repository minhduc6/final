package com.example.springsocial.repository;

import com.example.springsocial.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceDetailRepository  extends JpaRepository<InvoiceDetail, InvoiceDetailId>,InvoiceDetailCustomRepository {
    List<InvoiceDetail> findByInvoice(Invoice invoice);
}
