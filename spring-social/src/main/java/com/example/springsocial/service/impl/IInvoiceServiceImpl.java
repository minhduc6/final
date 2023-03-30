package com.example.springsocial.service.impl;

import com.example.springsocial.dto.InvoiceDetailDto;
import com.example.springsocial.dto.TypeTicketDto;
import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.Invoice;
import com.example.springsocial.model.InvoiceDetail;
import com.example.springsocial.repository.InvoiceDetailRepository;
import com.example.springsocial.repository.InvoiceRepository;
import com.example.springsocial.service.IInvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IInvoiceServiceImpl implements IInvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<InvoiceDetailDto> findByInvoiceId(Long id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        return invoiceDetailRepository.findByInvoice(invoice).stream()
                .map(item -> modelMapper.map(item, InvoiceDetailDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Invoice> findFilterInvoice(String keyword, String form, String to) {
        return invoiceRepository.filterInvoice(keyword,form,to);
    }

    @Override
    public Invoice updateStatus(Long id, Integer status) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Invoice", "id", id)
        );

        invoice.setStatus(status);
        invoiceRepository.save(invoice);
        return  invoice;
    }
}
