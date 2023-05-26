package com.example.springsocial.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventCategory implements Serializable {
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "eventId", column = @Column(name = "event_id", nullable = false)),
            @AttributeOverride(name = "categoryId", column = @Column(name = "category_id", nullable = false))
    })
    private EventCategoryId id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", nullable = false, insertable = false, updatable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false, insertable = false, updatable = false)
    private Category category;
}
