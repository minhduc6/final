package com.example.springsocial.repository;

import com.example.springsocial.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository  extends JpaRepository<Blog, Long> {
}
