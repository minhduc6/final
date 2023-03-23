package com.example.springsocial.controller;

import com.example.springsocial.dto.EventDto;
import com.example.springsocial.model.Event;
import com.example.springsocial.service.IEventService;
import com.example.springsocial.service.criteria.EventSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/api")
public class EventController {
    @Autowired
    private IEventService iEventService;

    @GetMapping("/event")
    public List<Event> getListEvent(@RequestParam(required = false) String from
            , @RequestParam(required = false) String to
            , @RequestParam(required = false) String address
            , @RequestParam(required = false) String... param) {


        Set<String> set = new TreeSet<String>(Arrays.asList(param));
        EventSearchCriteria searchCriteria = EventSearchCriteria.builder()
                .fromTime(from)
                .address(address)
                .toTime(to).
                categories(set).build();

        return iEventService.retrieveFilms(searchCriteria);
    }

    @GetMapping("/event/{id}")
    public EventDto getEventByID(@PathVariable("id") Long id) {
        return  iEventService.getEventById(id);
    }


}
