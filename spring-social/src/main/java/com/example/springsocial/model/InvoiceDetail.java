package com.example.springsocial.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceDetail {
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "invoiceId", column = @Column(name = "invoice_id", nullable = false)),
            @AttributeOverride(name = "ticketId", column = @Column(name = "ticket_id", nullable = false))
    })
    private InvoiceDetailId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false, insertable = false, updatable = false)
    private Invoice invoice;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false, insertable = false, updatable = false)
    private TypeTicket typeTicket;

    private Integer quantity;

}
