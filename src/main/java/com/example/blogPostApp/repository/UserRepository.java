package com.example.blogPostApp.repository;

import com.example.blogPostApp.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Userr, Long> {


}
