package com.example.springsocial.controller.admin;


import com.example.springsocial.dto.InvoiceDetailDto;
import com.example.springsocial.model.Category;
import com.example.springsocial.model.Invoice;
import com.example.springsocial.model.InvoiceDetail;
import com.example.springsocial.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public List<InvoiceDetailDto> getInvoiceDetailByID(@PathVariable Long id) {
        return iInvoiceService.findByInvoiceId(id);
    }
}
