package com.example.blogPostApp.service;

import com.example.blogPostApp.model.Authority;
import com.example.blogPostApp.model.Post;
import com.example.blogPostApp.model.Userr;
import com.example.blogPostApp.repository.AuthorityRepository;
import com.example.blogPostApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityRepository authorityRepository;

    public Optional<Userr> getById(Long id) {

        return userRepository.findById(id);
    }

    public Userr save(Userr user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRetypePassWord(passwordEncoder.encode(user.getRetypePassWord()));
        if (user.getId() == null) {
            if (user.getAuthorities().isEmpty()) {
                Set<Authority> authorities = new HashSet<>();
                authorityRepository.findById("ROLE_USER").ifPresent(authorities::add);
                user.setAuthorities(authorities);
            }
        }
        return userRepository.save(user);
    }
    public void delete(Userr user) {
        userRepository.delete(user);
    }

    public Optional<Userr> findByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }
    public List<Userr> getAllUsers() {

        return userRepository.findAll();
    }

}
//    public Userr editProfile(Userr user, Long id){
//        Optional<Userr> optionalUserr = userService.getById(id);
//        user = optionalUserr.get();
//
//        if (optionalUserr.isPresent()) {
//            Userr existingUser = optionalUserr.get();
//
//            existingUser.setFirstName();
//            existingUser.setBody(post.getBody());
//
//            postService.save(existingPost);
//        }
//
//    }


