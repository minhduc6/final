package com.example.springsocial.service.impl;

import com.example.springsocial.dto.EventDto;
import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.*;
import com.example.springsocial.payload.EventRequest;
import com.example.springsocial.payload.TypeTicketRequest;
import com.example.springsocial.repository.*;
import com.example.springsocial.repository.filter.EventSpecifications;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.IEventService;
import com.example.springsocial.service.criteria.EventSearchCriteria;
import com.example.springsocial.util.FileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IEventServiceImpl implements IEventService {


    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrganizersRepository organizersRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  TypeTicketRepository typeTicketRepository;


    @Override
    public Event createEvent(UserPrincipal userPrincipal, MultipartFile multipartFile, EventRequest eventRequest) {

        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        Organizers organizers = organizersRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Organizers", "user", user));

        Event event = new Event();
        event.setName(eventRequest.getName());
        event.setDescription(eventRequest.getDescription());
        event.setTime(eventRequest.getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.UK);
        LocalDate date = LocalDate.parse(eventRequest.getDay(), formatter);
        event.setDay(date);
        event.setAddress(eventRequest.getAddress());
        if(multipartFile != null){
            event.setImg(FileUtil.uploadFile(multipartFile));
        }
        event.setStatus(eventRequest.getStatus());
        event.setOrganizer(organizers);
        eventRepository.save(event);

        for (int i = 0; i < eventRequest.getListCategory().size(); i++) {
            Long categoryId = eventRequest.getListCategory().get(i);
            EventCategoryId eventCategoryId = EventCategoryId.builder()
                    .eventId(event.getId()).categoryId(categoryId).build();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
            EventCategory eventCategory = EventCategory.builder().id(eventCategoryId).event(event).category(category).build();
	 	    eventCategoryRepository.save(eventCategory);
        }

        if(eventRequest.getTicketList() != null) {
            for (int i = 0; i < eventRequest.getTicketList().size(); i++) {
                TypeTicket typeTicket = TypeTicket.builder()
                        .id(eventRequest.getTicketList().get(i).getId())
                        .name(eventRequest.getTicketList().get(i).getName())
                        .description(eventRequest.getTicketList().get(i).getDescription())
                        .price(eventRequest.getTicketList().get(i).getPrice())
                        .quantity(eventRequest.getTicketList().get(i).getQuantity())
                        .status(eventRequest.getTicketList().get(i).getStatus())
                        .event(event).build();
                typeTicketRepository.save(typeTicket);
            }
        }
        return event;
    }

    @Override
    @Transactional
    public Event updateEvent(Long id,UserPrincipal userPrincipal, MultipartFile multipartFile, EventRequest eventRequest) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        Organizers organizers = organizersRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Organizers", "user", user));
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", id));
        event.setName(eventRequest.getName());
        event.setDescription(eventRequest.getDescription());
        event.setTime(eventRequest.getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.UK);
        LocalDate date = LocalDate.parse(eventRequest.getDay(), formatter);
        event.setDay(date);
        event.setAddress(eventRequest.getAddress());
        if(multipartFile != null){
            event.setImg(FileUtil.uploadFile(multipartFile));
        }
        event.setStatus(eventRequest.getStatus());
        event.setOrganizer(organizers);
        eventRepository.save(event);

        List<EventCategory> categoryList = eventCategoryRepository.deleteAllByEvent(event);
        List<TypeTicket> ticketList = typeTicketRepository.deleteTypeTicketByEvent(event);

        for (int i = 0; i < eventRequest.getListCategory().size(); i++) {
            Long categoryId = eventRequest.getListCategory().get(i);

            EventCategoryId eventCategoryId = EventCategoryId.builder()
                    .eventId(event.getId()).categoryId(categoryId).build();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
            EventCategory eventCategory = EventCategory.builder().id(eventCategoryId).event(event).category(category).build();
            eventCategoryRepository.save(eventCategory);
        }

        for (int i = 0; i < eventRequest.getTicketList().size(); i++) {
            TypeTicket typeTicket = TypeTicket.builder()
                    .id(eventRequest.getTicketList().get(i).getId())
                    .name(eventRequest.getTicketList().get(i).getName())
                    .description(eventRequest.getTicketList().get(i).getDescription())
                    .price(eventRequest.getTicketList().get(i).getPrice())
                    .quantity(eventRequest.getTicketList().get(i).getQuantity())
                    .status(eventRequest.getTicketList().get(i).getStatus())
                    .event(event).build();
            System.out.println("ticket ID :" + typeTicket.getId());
            typeTicketRepository.save(typeTicket);
        }
        return event;
    }

    @Override
    public List<Event> getAll() {
       return eventRepository.findAll();
    }

    @Override
    public void deleteEvent(Long id) {
         eventRepository.deleteById(id);
    }

    @Override
    public List<Event> getAllHomePage(String search ,String from, String to,String address, Set<Long> cate) {
        return  eventRepository.findEventFilter(search,from,to,address,cate);
    }


    @Override
    public EventDto getEventById(Long id) {
        Event event = eventRepository.findById(id).get();
        Map<Event, List<Category>> resultsWithSameIdAndName = eventCategoryRepository.findAllByEvent(event).stream()
                .collect(Collectors.groupingBy(EventCategory::getEvent,
                        Collectors.mapping(d-> d.getCategory(), Collectors.toList())));
        List<TypeTicketRequest> ticketList = typeTicketRepository.findAllByEvent(event)
                .stream()
                .map(ticket -> modelMapper.map(ticket, TypeTicketRequest.class))
                .collect(Collectors.toList());
        Organizers organizers = organizersRepository.findById(event.getOrganizer().getId()).get();
        EventDto eventDto = modelMapper.map(event, EventDto.class);
        eventDto.setCategoryList(resultsWithSameIdAndName.get(event));
        eventDto.setOrganizers(organizers);
        eventDto.setTypeTickets(ticketList);
        return  eventDto;
    }
}
