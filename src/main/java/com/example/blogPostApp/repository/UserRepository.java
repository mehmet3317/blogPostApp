package com.example.blogPostApp.repository;

import com.example.blogPostApp.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Userr, Long> {
    Optional<Userr> findOneByEmail(String email);}

