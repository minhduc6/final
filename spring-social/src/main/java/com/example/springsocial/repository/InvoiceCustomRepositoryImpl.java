package com.example.springsocial.repository;

import com.example.springsocial.model.Invoice;
import com.example.springsocial.model.QInvoice;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

public class InvoiceCustomRepositoryImpl implements InvoiceCustomRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Invoice> filterInvoice(String keyword, String from, String to) {
        QInvoice invoice = QInvoice.invoice;
        JPAQueryFactory query = new JPAQueryFactory(em);
        BooleanBuilder builder = new BooleanBuilder();
        if(from != null && !from.trim().isEmpty() && to != null && !to.trim().isEmpty()) {
            LocalDate localDateFrom = LocalDate.parse(from);
            LocalDate localDateTo = LocalDate.parse(to);
            builder.and(invoice.times.between(localDateFrom.atStartOfDay(), localDateTo.atStartOfDay()));
        }
        if (keyword != null && !keyword.trim().isEmpty()) {
            builder.and(invoice.nameRecv.like("%"+keyword+"%"));
        }
        return query.selectFrom(invoice)
                .where(builder).distinct().fetch();
    }
}
