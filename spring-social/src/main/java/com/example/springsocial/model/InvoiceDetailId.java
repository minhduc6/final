package com.example.springsocial.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InvoiceDetailId implements Serializable {
    private static final long serialVersionUID = -7987680183531561896L;

    @Column(name = "invoice_id", nullable = false)
    @EqualsAndHashCode.Include
    private Long invoiceId;

    @Column(name = "ticket_id", nullable = false)
    @EqualsAndHashCode.Include
    private Long ticketId;


    @EqualsAndHashCode.Include
    private String serialCode;
}
