package com.example.blogPostApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getLoginPage(){
        return "LoginPage.html";
    }

    @PostMapping
    public String loginUser(@RequestParam(name = "username") String username,
                            @RequestParam(name = "password") String password){

        return "LoginPage.html";
    }
}
