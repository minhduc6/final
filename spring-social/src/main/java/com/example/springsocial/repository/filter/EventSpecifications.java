package com.example.springsocial.repository.filter;

import com.example.springsocial.model.*;
import com.example.springsocial.repository.EventRepository;
import com.example.springsocial.service.criteria.EventSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

public class EventSpecifications {


    private EventSpecifications() {
    }

    public static Specification<Event> createEventSpecifications(EventSearchCriteria searchCriteria) {
        return null;
    }


//    public static Specification<Event> findByAddress(String address) {
//        if (!address.isBlank()) {
//            return (root, query, builder) -> {
//                return builder.like(root.get(Event_.ADDRESS),"%"+address+"%");
//            };
//        }
//        return null;
//    }
//
//    public static Specification<Event> timeBetween(String fromTime, String toTime) {
//        if (fromTime == null || toTime == null) {
//            return (root, query, builder) -> {
//                return builder.greaterThanOrEqualTo(root.get(Event_.DAY), LocalDate.MIN);
//            };
//        } else {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            formatter = formatter.withLocale( Locale.UK);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
//            LocalDate dateFrom = LocalDate.parse(fromTime, formatter);
//            LocalDate dateTo = LocalDate.parse(toTime ,formatter);
//
//            System.out.println("Date from : " + dateFrom);
//            System.out.println("Date to : " + dateTo);
//
//            return (root, query, builder) -> {
//                return builder.between(root.get(Event_.DAY),dateFrom,dateTo);
//            };
//        }
//    }
//
//    public static Specification<Event> categoryIn(Set<String> categories) {
//        if (CollectionUtils.isEmpty(categories)) {
//            return null;
//        }
//        return (root, query, builder) -> {
//            Join<Event, EventCategory> eventCategoryJoin = root.join(Event_.EVENT_CATEGORIES);
//            Join<EventCategory, Category> categoryJoin = eventCategoryJoin.join(EventCategory_.CATEGORY);
//            return categoryJoin.get(Category_.NAME).in(categories);
//        };
//    }
}
