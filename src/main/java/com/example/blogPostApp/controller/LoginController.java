package com.example.blogPostApp.controller;

import com.example.blogPostApp.model.Userr;
import com.example.blogPostApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(){
        return "LoginPage";
    }

}
