package com.example.springsocial.controller.admin;

import com.example.springsocial.dto.EventDto;
import com.example.springsocial.model.Category;
import com.example.springsocial.model.Event;
import com.example.springsocial.model.EventCategory;
import com.example.springsocial.repository.EventCategoryRepository;
import com.example.springsocial.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/admin")
public class TestController {
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private EventRepository eventRepository;



    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public  EventDto  getAllCategory() {
        Event event = eventRepository.findById(1L).get();
        Map<Event, List<Category>>  resultsWithSameIdAndName = eventCategoryRepository.findAllByEvent(event).stream()
                .collect(Collectors.groupingBy(EventCategory::getEvent,
                        Collectors.mapping(d-> d.getCategory(), Collectors.toList())));
        EventDto eventDto = modelMapper.map(event, EventDto.class);
        eventDto.setCategoryList(resultsWithSameIdAndName.get(event));
        return  eventDto;
    }
    @GetMapping("/test/bet")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public  List<Event>  getBeetween() {

       eventCategoryRepository.deleteAllByEvent_Id(1L);
       return  null;
    }
}
