package com.example.springsocial.controller;

import com.example.springsocial.dto.TypeTicketDto;
import com.example.springsocial.model.TypeTicket;
import com.example.springsocial.service.ITypeTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TypeTicketController {

    @Autowired
    private ITypeTicketService iTypeTicketService;
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/type-ticket/{id}")
    public List<TypeTicketDto> getTypeTicketByEventId(@PathVariable("id") Long id) {
        return  iTypeTicketService.findTicketByEvent(id);
    }

}
