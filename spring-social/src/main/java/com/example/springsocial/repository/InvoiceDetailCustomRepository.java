package com.example.springsocial.repository;

import com.example.springsocial.dto.*;
import com.example.springsocial.model.Event;
import com.querydsl.core.Tuple;

import java.util.List;

public interface InvoiceDetailCustomRepository {
    List<StatisticalTicket> statisticalTicket(Long id);

    List<SalesTicket> salesTicketEvent(Long id);

    InvoiceDTO detailInvoice(Long invoice_id);

    List<MyTicketDTO> getTicketByInvoice(Long id);


}
