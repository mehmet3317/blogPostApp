package com.example.blogPostApp.repository;

import com.example.blogPostApp.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Userr, Long> {

     Userr findByUserName(String username);

}
