package com.example.springsocial.repository;

import com.example.springsocial.model.Category;
import com.example.springsocial.model.Event;
import com.example.springsocial.model.EventCategory;
import com.example.springsocial.model.EventCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory, EventCategoryId> {
    List<EventCategory> findAllByEvent(Event event);
    List<EventCategory> deleteAllByEvent_Id(Long id);

    List<EventCategory> deleteAllByEvent(Event event);

}
