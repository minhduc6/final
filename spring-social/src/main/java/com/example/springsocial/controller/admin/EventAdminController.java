package com.example.springsocial.controller.admin;



import com.example.springsocial.dto.EventDto;
import com.example.springsocial.model.Event;
import com.example.springsocial.payload.EventRequest;

import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.IEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/admin")
public class EventAdminController {

    @Autowired
    private IEventService eventService;


    @PostMapping(value = "/event",consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @PreAuthorize("hasRole('ADMIN')")
    public Event createEvent(@CurrentUser UserPrincipal userPrincipal, @RequestPart(value = "file" ,required = false) MultipartFile file, @RequestPart("event") String eventRequest) {
        EventRequest eventRequest1 = new EventRequest();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            eventRequest1 = objectMapper.readValue(eventRequest,EventRequest.class);
        }catch (IOException err){
            System.out.println("ERR " + err);
        }
        System.out.println("Event Request String :" + eventRequest);
        System.out.println("UserPricipal :" + userPrincipal);
        System.out.println("File :" + file);
        System.out.println("Event Request :" + eventRequest1);
        return eventService.createEvent(userPrincipal,file,eventRequest1);
    }

    @PutMapping(value = "/event/{id}",consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @PreAuthorize("hasRole('ADMIN')")
    public Event updateEvent(@PathVariable Long id,@CurrentUser UserPrincipal userPrincipal, @RequestPart(value = "file" ,required = false) MultipartFile file, @RequestPart("event") String eventRequest) {
        EventRequest eventRequest1 = new EventRequest();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            eventRequest1 = objectMapper.readValue(eventRequest,EventRequest.class);
        }catch (IOException err){
            System.out.println("ERR " + err);
        }
        System.out.println("Event Request String :" + eventRequest);
        System.out.println("UserPricipal :" + userPrincipal);
        System.out.println("File :" + file);
        System.out.println("Event Request :" + eventRequest1);
        return eventService.updateEvent(id,userPrincipal,file,eventRequest1);
    }

    @GetMapping("/event/{id}")
    public EventDto getEventByID(@PathVariable("id") Long id) {
        return eventService.getEventById(id);
    }
}
