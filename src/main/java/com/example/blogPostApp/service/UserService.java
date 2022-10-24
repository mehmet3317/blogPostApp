package com.example.blogPostApp.service;

import com.example.blogPostApp.model.Userr;
import com.example.blogPostApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Userr save(Userr user){

        return userRepository.save(user);
    }
    public Optional<Userr> findUserByEmail(String email){

        return userRepository.findByEmail(email);
    }

    public Userr findUserByUsername(String username){

        return userRepository.findByUserName(username);
    };



}
