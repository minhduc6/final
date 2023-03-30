package com.example.springsocial.repository;

import com.example.springsocial.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.springsocial.model.QCategory;
import com.example.springsocial.model.QEvent;
import com.example.springsocial.model.QEventCategory;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Slf4j
public class EventCustomRepositoryImpl implements EventCustomRepository {


    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Event> findEventFilter(String search ,String from, String to,String address, Set<Long> cate) {
        QEvent event = QEvent.event;
        QEventCategory eventCategory = QEventCategory.eventCategory;
        JPAQueryFactory query = new JPAQueryFactory(em);
        BooleanBuilder builder = new BooleanBuilder();
        if(from != null && !from.trim().isEmpty() && to != null && !to.trim().isEmpty()) {
            LocalDate localDateFrom = LocalDate.parse(from);
            LocalDate localDateTo = LocalDate.parse(to);
            builder.and(event.day.between(localDateFrom,localDateTo));
        }
        if(!cate.isEmpty()){
            builder.and(eventCategory.id.categoryId.in(cate));
        }
        if(address != null && !address.trim().isEmpty()){
            builder.and(event.address.eq(address));
        }
        if(search != null && !search.trim().isEmpty()){
            builder.and(event.name.like("%"+search+"%"));
        }
        builder.and(event.status.eq(1));
        return  query.selectFrom(event)
                .innerJoin(event.eventCategories, eventCategory).where(builder).distinct().fetch();
    }
}
