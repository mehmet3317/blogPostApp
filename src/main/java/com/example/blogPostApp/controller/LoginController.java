package com.example.blogPostApp.controller;

import com.example.blogPostApp.model.Userr;
import com.example.blogPostApp.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller

public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String getLoginPage(){
        return "LoginPage.html";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam(required=false,name = "username") String username,
                            @RequestParam( required=false,name = "password") String password,
                            HttpSession session,
                            RedirectAttributes redirectAttributes){

        Userr userr = userService.findUserByUsername(username);
        if(userr!=null){

            if(userr.getPassword() != null && userr.getPassword().equals(password)){

                //successfull login //
                session.setAttribute("isAuthenticated",true);
                session.setAttribute("loggeduser", userr);
                return "redirect:/";

            }

        }
        redirectAttributes.addFlashAttribute("loginError", true);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String getLogOut(HttpSession session){

        session.setAttribute("isAuthenticated",false);
        session.setAttribute("loggeduser", null);
        return "redirect:/";
    }

}
