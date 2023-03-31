package com.example.springsocial.service.impl;


import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.Category;
import com.example.springsocial.model.Organizers;
import com.example.springsocial.payload.OrganizerRequest;
import com.example.springsocial.repository.OrganizersRepository;
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
    public Organizers updateOrganizer(Long id, OrganizerRequest organizerRequest , MultipartFile file) {
        Organizers organizers = organizersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Organizers", "id", id)
        );
        organizers.setName(organizerRequest.getName());
        organizers.setEmail(organizerRequest.getEmail());
        organizers.setCodeBusiness(organizerRequest.getCodeBusiness());
        organizers.setCity(organizerRequest.getCity());
        organizers.setDistrict(organizerRequest.getDistrict());
        organizers.setWard(organizerRequest.getDistrict());
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
