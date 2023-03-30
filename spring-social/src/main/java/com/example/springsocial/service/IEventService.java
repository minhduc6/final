package com.example.springsocial.service;


import com.example.springsocial.dto.EventDto;
import com.example.springsocial.model.Event;
import com.example.springsocial.payload.EventRequest;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.criteria.EventSearchCriteria;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IEventService {

     Event createEvent(UserPrincipal userPrincipal , MultipartFile multipartFile, EventRequest eventRequest);

     Event updateEvent(Long id ,UserPrincipal userPrincipal , MultipartFile multipartFile, EventRequest eventRequest);
     List<Event> getAll();

     void deleteEvent(Long id);

     List<Event> getAllHomePage(String search ,String from, String to,String address, Set<Long> cate);

     EventDto getEventById(Long id);
}
