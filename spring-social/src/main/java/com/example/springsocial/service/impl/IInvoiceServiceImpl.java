package com.example.springsocial.service.impl;

import com.example.springsocial.dto.InvoiceDTO;
import com.example.springsocial.dto.InvoiceDetailDto;
import com.example.springsocial.dto.MyTicketDTO;
import com.example.springsocial.dto.TypeTicketDto;
import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.Invoice;
import com.example.springsocial.model.InvoiceDetail;
import com.example.springsocial.repository.InvoiceDetailRepository;
import com.example.springsocial.repository.InvoiceRepository;
import com.example.springsocial.service.IInvoiceService;
import com.example.springsocial.service.ISendEmailService;
import com.google.zxing.WriterException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IInvoiceServiceImpl implements IInvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ISendEmailService iSendEmailService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public InvoiceDTO findByInvoiceId(Long id) {
        return  invoiceDetailRepository.detailInvoice(id);
    }

    @Override
    public List<Invoice> findFilterInvoice(String keyword, String form, String to) {
        return invoiceRepository.filterInvoice(keyword,form,to);
    }

    @Override
    public void sendEmail(String email , Long id) throws MessagingException, IOException, WriterException {
        List<MyTicketDTO> myTicketDTOList = invoiceDetailRepository.getTicketByInvoice(id);

        for (int i = 0; i <  myTicketDTOList.size(); i++) {
               iSendEmailService.qrCode(myTicketDTOList.get(i).getSerialCode(),email,"This is email body.",
                       "This email subject", "" +
                               "qr-codes//image.png");
        }
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
