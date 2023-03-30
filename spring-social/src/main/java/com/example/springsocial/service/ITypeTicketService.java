package com.example.springsocial.service;

import com.example.springsocial.dto.TypeTicketDto;
import com.example.springsocial.model.Event;
import com.example.springsocial.model.TypeTicket;

import java.util.List;

public interface ITypeTicketService {
    List<TypeTicketDto>  findTicketByEvent(Long id);
}
