package com.example.springsocial.repository;

import com.example.springsocial.model.Category;
import com.example.springsocial.model.Invoice;
import com.example.springsocial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>,InvoiceCustomRepository {
    List<Invoice> findAllByUserOrder(User user);
}
