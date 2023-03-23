package com.example.springsocial.repository;

import com.example.springsocial.model.EventCategory;
import com.example.springsocial.model.EventCategoryId;
import com.example.springsocial.model.InvoiceDetail;
import com.example.springsocial.model.InvoiceDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetailRepository  extends JpaRepository<InvoiceDetail, InvoiceDetailId> {
}
