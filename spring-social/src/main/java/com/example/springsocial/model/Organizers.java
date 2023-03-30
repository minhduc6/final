package com.example.springsocial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@SuperBuilder
public class Organizers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String codeBusiness;

    private String image;

    private String sdt;

    private String email;

    private String district;

    private String city;

    private String ward;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organizer")
    @JsonIgnore
    private Set<Event> listEvent = new HashSet<>();


    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
