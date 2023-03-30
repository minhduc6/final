package com.example.springsocial.repository;

import com.example.springsocial.dto.SalesTicket;
import com.example.springsocial.dto.StatisticalTicket;
import com.example.springsocial.model.Event;
import com.example.springsocial.model.QInvoiceDetail;
import com.example.springsocial.model.QTypeTicket;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;



public class InvoiceDetailCustomRepositoryImpl implements InvoiceDetailCustomRepository{

    @PersistenceContext
    private EntityManager em;
    @Override
    public List<StatisticalTicket> statisticalTicket(Long id) {
        QInvoiceDetail invoiceDetail = QInvoiceDetail.invoiceDetail;
        QTypeTicket typeTicket = QTypeTicket.typeTicket;
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Tuple> tuples =  query.selectFrom(invoiceDetail)
                .innerJoin(invoiceDetail.typeTicket,typeTicket)
                .select(invoiceDetail.typeTicket.id,invoiceDetail.quantity.sum())
                .groupBy(invoiceDetail.typeTicket.id)
                .where(invoiceDetail.typeTicket.event.id.eq(id))
                .fetch();
        List<StatisticalTicket> statisticalTickets = new ArrayList<>();

        for (int i = 0; i < tuples.size(); i++) {
            Long row = (Long) tuples.get(i).toArray()[0];
            Integer value = (Integer) tuples.get(i).toArray()[1];
            StatisticalTicket statisticalTicket = new StatisticalTicket(row,value);
            statisticalTickets.add(statisticalTicket);
        }
        return  statisticalTickets;
    }

    @Override
    public List<SalesTicket> salesTicketEvent(Long id) {
        QInvoiceDetail invoiceDetail = QInvoiceDetail.invoiceDetail;
        QTypeTicket typeTicket = QTypeTicket.typeTicket;
        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Tuple> tuples = query.selectFrom(invoiceDetail)
                .innerJoin(invoiceDetail.typeTicket,typeTicket)
                .select(invoiceDetail.typeTicket.id,invoiceDetail.typeTicket.price,invoiceDetail.quantity.sum())
                .groupBy(invoiceDetail.typeTicket.id)
                .where(invoiceDetail.typeTicket.event.id.eq(id))
                .fetch();

        System.out.println("Tupels : " + tuples);
        List<SalesTicket> salesTickets = new ArrayList<>();
        for (int i = 0; i < tuples.size(); i++) {
            Long row0 = (Long) tuples.get(i).toArray()[0];
            Float row1 = (Float) tuples.get(i).toArray()[1];
            Integer row2 = (Integer) tuples.get(i).toArray()[2];
            SalesTicket salesTicket = new SalesTicket(row0,row2,row1,row1*row2);
            salesTickets.add(salesTicket);
        }
        return  salesTickets;
    }
}
