package com.example.springsocial.service.criteria;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EventSearchCriteria {

    private String fromTime;

    private String toTime;

    private String address;
    private Set<String> categories;
}
