package com.example.springsocial.service;

import com.example.springsocial.model.Category;
import com.example.springsocial.model.Organizers;
import com.example.springsocial.payload.OrganizerRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IOrganizersService {
    List<Organizers> getAll();

    Optional<Organizers> getByID(Long id);


    Organizers updateOrganizer(Long id , OrganizerRequest organizerRequest , MultipartFile file);

    Organizers deleteOrganizers(Long id);
}
