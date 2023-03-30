package com.example.springsocial.repository;

import com.example.springsocial.model.Event;

import java.util.List;
import java.util.Set;

public interface EventCustomRepository {
    List<Event> findEventFilter(String search ,String from , String to, String address, Set<Long> cate);
}
