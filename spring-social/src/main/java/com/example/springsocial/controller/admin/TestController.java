package com.example.springsocial.controller.admin;

import com.example.springsocial.dto.*;
import com.example.springsocial.model.Category;
import com.example.springsocial.model.Event;
import com.example.springsocial.model.EventCategory;
import com.example.springsocial.model.InvoiceDetail;
import com.example.springsocial.repository.EventCategoryRepository;
import com.example.springsocial.repository.EventRepository;
import com.example.springsocial.repository.InvoiceDetailRepository;
import com.example.springsocial.service.ISendEmailService;
import com.google.zxing.WriterException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
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
    private ISendEmailService iSendEmailService;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;



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
    public  String  getQrcode(@RequestParam String data) throws IOException, WriterException, MessagingException {
       return  iSendEmailService.qrCode(data,"duc0611111@gmail.com",
               "This is email body.",
               "This email subject", "" +
                       "qr-codes//image.png");
    }


    @GetMapping("/test/statistical/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<StatisticalTicket> getStatistical(@PathVariable Long id)  {
        return  invoiceDetailRepository.statisticalTicket(id);
    }

    @GetMapping("/test/sales/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<MyTicketDTO> getSaleTicket(@PathVariable Long id)  {
        return  invoiceDetailRepository.getTicketByInvoice(id);
    }
}
