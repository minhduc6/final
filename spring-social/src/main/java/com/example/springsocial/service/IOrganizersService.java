package com.example.springsocial.service;

import com.example.springsocial.model.Category;
import com.example.springsocial.model.Event;
import com.example.springsocial.model.Organizers;
import com.example.springsocial.payload.OrganizerRequest;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IOrganizersService {
    List<Organizers> getAll();

    Optional<Organizers> getByID(Long id);

    Optional<Organizers> getByUser(UserPrincipal userPrincipal);

    List<Event> getListEventByOrganizer(UserPrincipal userPrincipal);

    Organizers createOrganizer(@CurrentUser UserPrincipal userPrincipal, OrganizerRequest organizerRequest , MultipartFile file);

    Organizers updateOrganizer(Long id , OrganizerRequest organizerRequest , MultipartFile file);

    Organizers deleteOrganizers(Long id);
}
