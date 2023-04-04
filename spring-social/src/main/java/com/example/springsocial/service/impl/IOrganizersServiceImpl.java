package com.example.springsocial.service.impl;


import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.Event;
import com.example.springsocial.model.Organizers;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.OrganizerRequest;
import com.example.springsocial.repository.EventRepository;
import com.example.springsocial.repository.OrganizersRepository;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.IOrganizersService;
import com.example.springsocial.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class IOrganizersServiceImpl implements IOrganizersService {

    @Autowired
    private OrganizersRepository organizersRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Organizers> getAll() {
        return organizersRepository.findAll();
    }

    @Override
    public Optional<Organizers> getByID(Long id) {
        return Optional.ofNullable(organizersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organizers", "id", id)));
    }

    @Override
    public Optional<Organizers> getByUser(UserPrincipal userPrincipal) {
        return Optional.ofNullable(organizersRepository.findByUser(userRepository.findById(userPrincipal.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Organizers", "id", userPrincipal.getId())));
    }

    @Override
    public List<Event> getListEventByOrganizer(UserPrincipal userPrincipal) {
        Organizers organizers = organizersRepository.findByUser(Optional.of(userRepository.findById(userPrincipal.getId()).get())).get();
        return  eventRepository.findAllByOrganizer(organizers);
    }

    @Override
    public Organizers createOrganizer(UserPrincipal userPrincipal ,OrganizerRequest organizerRequest, MultipartFile file) {
        User user = userRepository.findById(userPrincipal.getId()).get();
        Organizers organizers = new Organizers();
        organizers.setName(organizerRequest.getName());
        organizers.setEmail(organizerRequest.getEmail());
        organizers.setCodeBusiness(organizerRequest.getCodeBusiness());
        organizers.setCity(organizerRequest.getCity());
        organizers.setSdt(organizerRequest.getSdt());
        organizers.setDistrict(organizerRequest.getDistrict());
        organizers.setWard(organizerRequest.getWard());
        organizers.setAddress(organizerRequest.getAddress());
        organizers.setUser(user);
        if(file != null){
            organizers.setImage(FileUtil.uploadFile(file));
        }
        organizersRepository.save(organizers);
        return organizers;
    }

    @Override
    public Organizers updateOrganizer(Long id, OrganizerRequest organizerRequest , MultipartFile file) {
        Organizers organizers = organizersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Organizers", "id", id)
        );
        organizers.setName(organizerRequest.getName());
        organizers.setEmail(organizerRequest.getEmail());
        organizers.setCodeBusiness(organizerRequest.getCodeBusiness());
        organizers.setCity(organizerRequest.getCity());
        organizers.setSdt(organizerRequest.getSdt());
        organizers.setDistrict(organizerRequest.getDistrict());
        organizers.setWard(organizerRequest.getWard());
        organizers.setAddress(organizerRequest.getAddress());
        if(file != null){
            organizers.setImage(FileUtil.uploadFile(file));
        }
        organizersRepository.save(organizers);
        return organizers;
    }

    @Override
    public Organizers deleteOrganizers(Long id) {
        Organizers organizers = organizersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Organizers", "id", id)
        );
        organizersRepository.deleteById(id);
        return organizers;
    }
}
