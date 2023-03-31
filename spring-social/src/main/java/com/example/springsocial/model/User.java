package com.example.springsocial.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude="organizers")
@SuperBuilder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PROVIDER_USER_ID")
    private String providerUserId;


    @Column(unique = true)
    private String email;


    @Column(name = "enabled", columnDefinition = "BIT", length = 1)
    private boolean enabled;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    protected LocalDate modifiedDate;

    private String password;

    private String provider;

    private String imgUrl;

    private Integer statusLogin;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Organizers organizers;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userOrder")
    @JsonIgnore
    private Set<Invoice> listInvoices = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    private Set<Role> roles;


}