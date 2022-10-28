package com.example.blogPostApp.controller;

import com.example.blogPostApp.model.Userr;
import com.example.blogPostApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        Userr user = new Userr();
        model.addAttribute("user", user);
        return "registerPage";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute Userr user, Model model) {

         user.setRetypePassWord(user.getPassword());


        if(user.getPassword() == user.getRetypePassWord()){

            userService.save(user);
            return "redirect:/";

        }
        else{
            model.addAttribute("message", "Your old password is incorrect.");
            return "redirect:/register";
        }


    }
}
