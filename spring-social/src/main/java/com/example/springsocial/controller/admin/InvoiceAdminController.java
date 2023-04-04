package com.example.springsocial.controller.admin;


import com.example.springsocial.dto.InvoiceDTO;
import com.example.springsocial.model.Invoice;
import com.example.springsocial.service.IInvoiceService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class InvoiceAdminController {

    @Autowired
    private IInvoiceService iInvoiceService;

    @GetMapping("/invoice")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Invoice> getAllInvoice() {
        return iInvoiceService.findAllInvoice();
    }


    @GetMapping("/filter/invoice")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Invoice> getFilterInvoice(@RequestParam(required = false) String keyword , @RequestParam(required = false) String from , @RequestParam(required = false) String to) {
        return iInvoiceService.findFilterInvoice(keyword, from, to);
    }

    @PutMapping("/updateInvoice/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Invoice updateStatus(@PathVariable Long id ,@RequestParam Integer status) {
        return iInvoiceService.updateStatus(id,status);
    }

    @GetMapping("/invoice/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public InvoiceDTO getInvoiceDetailByID(@PathVariable Long id) {
        return iInvoiceService.findByInvoiceId(id);
    }


    @GetMapping("/sendMail/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void sendEmail(@PathVariable Long id,@RequestParam String email) throws MessagingException, IOException, WriterException {
        System.out.println("Email :" + email);
        System.out.println("LONG ID :" + id);
        iInvoiceService.sendEmail(email,id);
    }

}
