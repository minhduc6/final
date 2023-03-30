package com.example.springsocial.repository;

import com.example.springsocial.dto.SalesTicket;
import com.example.springsocial.dto.StatisticalTicket;
import com.example.springsocial.model.Event;
import com.querydsl.core.Tuple;

import java.util.List;

public interface InvoiceDetailCustomRepository {
    List<StatisticalTicket> statisticalTicket(Long id);

    List<SalesTicket> salesTicketEvent(Long id);
}
