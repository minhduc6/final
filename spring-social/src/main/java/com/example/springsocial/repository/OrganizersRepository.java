package com.example.springsocial.repository;

import com.example.springsocial.model.Organizers;
import com.example.springsocial.model.Role;
import com.example.springsocial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizersRepository extends JpaRepository<Organizers, Long> {
    Optional<Organizers> findByUser(User user);

}
