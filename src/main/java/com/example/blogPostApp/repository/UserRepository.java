package com.example.blogPostApp.repository;

import com.example.blogPostApp.model.Userr;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Userr, Long> {



     Userr findByUserName(String username);
     Optional<Userr> findByEmail(String email);
}
