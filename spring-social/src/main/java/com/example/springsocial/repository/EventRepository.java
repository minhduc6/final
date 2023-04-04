package com.example.springsocial.repository;

import com.example.springsocial.model.Event;
import com.example.springsocial.model.Organizers;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long>,EventCustomRepository, JpaSpecificationExecutor<Event> {

    List<Event> findAllByOrganizer(Organizers organizers);

}
