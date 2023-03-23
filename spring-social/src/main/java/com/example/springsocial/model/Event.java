package com.example.springsocial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;
    private String time;
    private LocalDate day;
    private String img;
    private String address;
    private Integer status;
    private LocalDateTime create_at;
    private LocalDateTime update_at;


    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "event" ,orphanRemoval = true)
    private Set<TypeTicket> listTypeTicket = new HashSet<>();


    @JsonIgnore
    @OneToMany( fetch = FetchType.EAGER, mappedBy = "event",orphanRemoval = true)
    private Set<EventCategory> eventCategories = new HashSet<EventCategory>(0);

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = true)
    private Organizers organizer;

}