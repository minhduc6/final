package com.example.springsocial.controller.admin;

import com.example.springsocial.model.Event;
import com.example.springsocial.model.Organizers;
import com.example.springsocial.payload.EventRequest;
import com.example.springsocial.payload.OrganizerRequest;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.IOrganizersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class OrganizersAdminController {

    @Autowired
    private IOrganizersService iOrganizersService;
    @GetMapping("/organizers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Organizers> getOrganizers() {
        return  iOrganizersService.getAll();
    }
    @GetMapping("/organizer/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Organizers> getOrganizerById(@PathVariable Long id) {
        return  iOrganizersService.getByID(id);
    }

    @PutMapping(value = "/organizer/{id}",consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @PreAuthorize("hasRole('ADMIN')")
    public Organizers updateOrganizer(@PathVariable Long id, @RequestPart(value = "file" ,required = false) MultipartFile file, @RequestPart("organizer") String organizerRequest) {
        OrganizerRequest organizerRequest1 = new OrganizerRequest();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            organizerRequest1 = objectMapper.readValue(organizerRequest,OrganizerRequest.class);
        }catch (IOException err){
            System.out.println("ERR " + err);
        }
        return  iOrganizersService.updateOrganizer(id,organizerRequest1,file);
    }

    @DeleteMapping("/organizer/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Organizers deleteOrganizer(@PathVariable("id") Long id) {
        return  iOrganizersService.deleteOrganizers(id);
    }

}
