package com.example.springsocial.repository;

import com.example.springsocial.dto.*;
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
                .select(invoiceDetail.typeTicket.name,invoiceDetail.count())
                .groupBy(invoiceDetail.typeTicket.id)
                .where(invoiceDetail.typeTicket.event.id.eq(id))
                .fetch();
        List<StatisticalTicket> statisticalTickets = new ArrayList<>();

        for (int i = 0; i < tuples.size(); i++) {
            String  row = (String) tuples.get(i).toArray()[0];
            Long value = (Long) tuples.get(i).toArray()[1];
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
                .select(invoiceDetail.typeTicket.id,invoiceDetail.typeTicket.price,invoiceDetail.count())
                .groupBy(invoiceDetail.typeTicket.id)
                .where(invoiceDetail.typeTicket.event.id.eq(id))
                .fetch();

        System.out.println("Tupels : " + tuples);
        List<SalesTicket> salesTickets = new ArrayList<>();
        for (int i = 0; i < tuples.size(); i++) {
            Long row0 = (Long) tuples.get(i).toArray()[0];
            Float row1 = (Float) tuples.get(i).toArray()[1];
            Long row2 = (Long) tuples.get(i).toArray()[2];
            SalesTicket salesTicket = new SalesTicket(row0,row2,row1,row1*row2);
            salesTickets.add(salesTicket);
        }
        return  salesTickets;
    }

    @Override
    public InvoiceDTO detailInvoice(Long invoice_id) {
        QInvoiceDetail invoiceDetail = QInvoiceDetail.invoiceDetail;
        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Tuple> tuples = query.selectFrom(invoiceDetail)
                .select(invoiceDetail.typeTicket.name,invoiceDetail.typeTicket.price,invoiceDetail.count())
                .groupBy(invoiceDetail.typeTicket.id)
                .where(invoiceDetail.id.invoiceId.eq(invoice_id))
                .fetch();
        List<DetailInvoice> detailInvoices = new ArrayList<>();

        Float sum = 0f;
        for (int i = 0; i < tuples.size(); i++) {
            String row0 = (String) tuples.get(i).toArray()[0];
            Float row2 = (Float) tuples.get(i).toArray()[1];
            Long row3 = (Long) tuples.get(i).toArray()[2];
            DetailInvoice detailInvoice = new DetailInvoice(row0,row2,row3);
            detailInvoices.add(detailInvoice);
            sum += row2*row3;
        }
        System.out.println("TUple : " + tuples);
        InvoiceDTO invoiceDTO = new InvoiceDTO(detailInvoices,sum);
        invoiceDTO.setAmount(sum);
        return invoiceDTO;
    }

    @Override
    public List<MyTicketDTO> getTicketByInvoice(Long id) {
        QInvoiceDetail invoiceDetail = QInvoiceDetail.invoiceDetail;
        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Tuple> tuples = query.selectFrom(invoiceDetail)
                .select(invoiceDetail.typeTicket.name,invoiceDetail.id.serialCode,invoiceDetail.typeTicket.price)
                .where(invoiceDetail.id.invoiceId.eq(id))
                .fetch();
        System.out.println("Tuple : " + tuples);

        List<MyTicketDTO> myTicketDTOList = new ArrayList<>();

        for (int i = 0; i < tuples.size(); i++) {
            String row0 = (String) tuples.get(i).toArray()[0];
            String row1 = (String) tuples.get(i).toArray()[1];
            Float row2 = (Float) tuples.get(i).toArray()[2];
            MyTicketDTO myTicketDTO = new MyTicketDTO(row0,row1,row2);
            myTicketDTOList.add(myTicketDTO);
        }
        return myTicketDTOList;
    }


}
