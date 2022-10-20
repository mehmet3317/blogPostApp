package com.example.blogPostApp.controller;

import com.example.blogPostApp.model.Userr;
import com.example.blogPostApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        Userr user = new Userr();

        model.addAttribute("user", user);

        return "registerPage";

    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute Userr user) {
        userService.save(user);
        return "redirect:/";

    }
}