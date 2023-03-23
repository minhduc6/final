package com.example.springsocial.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class TypeTicket {

    @Id
    private Long id;

    @NotBlank
    private String name;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    private float price;

    private Integer quantity;

    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;


}
