package com.example.springsocial.service.impl;

import com.example.springsocial.dto.TypeTicketDto;
import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.Event;
import com.example.springsocial.model.TypeTicket;
import com.example.springsocial.payload.TypeTicketRequest;
import com.example.springsocial.repository.EventRepository;
import com.example.springsocial.repository.TypeTicketRepository;
import com.example.springsocial.service.ITypeTicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ITypeTicketServiceImpl  implements ITypeTicketService {

    @Autowired
    private TypeTicketRepository typeTicketRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventRepository eventRepository;
    @Override
    public List<TypeTicketDto> findTicketByEvent(Long id) {
        Event event =  eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", id)
        );
        return typeTicketRepository.findAllByEvent(event)
                .stream()
                .map(ticket -> modelMapper.map(ticket, TypeTicketDto.class))
                .collect(Collectors.toList());
    }
}
