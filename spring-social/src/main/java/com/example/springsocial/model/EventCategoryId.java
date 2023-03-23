package com.example.springsocial.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Embeddable
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EventCategoryId implements Serializable {

    private static final long serialVersionUID = -7987680183531561896L;

    @Column(name = "event_id", nullable = false)
    @EqualsAndHashCode.Include
    private Long eventId;

    @Column(name = "category_id", nullable = false)
    @EqualsAndHashCode.Include
    private Long categoryId;
}