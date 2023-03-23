package com.example.springsocial.repository;

import com.example.springsocial.model.Event;
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
//    @Query(nativeQuery =true,value = "select * from event inner  join event_category ec on event.id = ec.event_id " +
//            "where category_id IN (:cates) " +
//            "group by  event.id")
//    List<Event> findByEventByCate(@Param("cates") List<Integer> cates);

    List<Event> findAll(@Nullable Specification<Event> spec);


}
