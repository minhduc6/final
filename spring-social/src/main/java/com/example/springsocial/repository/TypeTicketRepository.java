package com.example.springsocial.repository;

import com.example.springsocial.model.Event;
import com.example.springsocial.model.TypeTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeTicketRepository extends JpaRepository<TypeTicket,Long> {
    List<TypeTicket> findAllByEvent(Event event);

    List<TypeTicket> deleteTypeTicketByEvent(Event event);
}
