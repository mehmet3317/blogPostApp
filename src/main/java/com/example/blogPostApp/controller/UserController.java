package com.example.blogPostApp.controller;

import com.example.blogPostApp.model.Post;
import com.example.blogPostApp.model.Userr;
import com.example.blogPostApp.repository.UserRepository;
import com.example.blogPostApp.service.PostService;
import com.example.blogPostApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{email}")
    public String getUser(@PathVariable String  email, Model model) {
        Optional<Userr> optionalPost = this.userService.findByEmail(email);

        if (optionalPost.isPresent()) {
            Userr user = optionalPost.get();
            model.addAttribute("user", user);
            return "userDetailsPage";
        } else {
            return "404";
        }
    }


    @GetMapping("/users/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getUserForEdit(@PathVariable Long id, Model model, Principal principal) {
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        Optional<Userr> optionalUserr = this.userService.getById(id);
        // Check if current logged in user is an owner and so has the right for modifications to happen
        if (optionalUserr.isPresent()) {
            Userr user = optionalUserr.get();

            // Check if current logged in user is owner
            if (authUsername.equals(user.getEmail())){
                model.addAttribute("user", user);
                System.err.println("EDIT user: " + user); // for testing debugging purposes
                return "editUserProfile";
            } else {
                System.err.println("Current User has no permissions to edit anything on post by id: " + id); // for testing debugging purposes
                return "error";

            }
        } else {
            System.err.println("Could not find a post by id: " + id); // for testing debugging purposes
            return "error";
        }
    }

    @PostMapping("/users/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updateUser(@PathVariable Long id, Userr user, BindingResult result, Model model) {

        Optional<Userr> optionalUserr = userService.getById(id);

        if (optionalUserr.isPresent()) {
            Userr existingUser = optionalUserr.get();

            model.addAttribute("user",existingUser);

            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setCity(user.getCity());
            existingUser.setStreet(user.getStreet());
            existingUser.setHouseNr(user.getHouseNr());
            existingUser.setZIP(user.getZIP());

            userRepository.save(existingUser);
            return "redirect:/users/" + existingUser.getEmail();
        }
        else return "error";
       // return "redirect:/users/" + .getEmail();

    }
    @GetMapping("/users/{id}/delete")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String deletePost(@PathVariable Long id, Principal principal) {

        // Just curious  what if we get username from Principal instead of SecurityContext
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        Optional<Userr> optionalUser = this.userService.getById(id);
        // Check if current logged in user is an owner and so has the right for modifications to happen
        if (optionalUser.isPresent()) {
            Userr user = optionalUser.get();
            // Check if current logged in user is owner
            if (authUsername.equals(user.getEmail())) {
                // if so then it is safe to remove post from database
                this.userService.delete(user);
                System.err.println("DELETED user: " + user); // for testing debugging purposes
                return "redirect:/";
            } else {
                System.err.println("Current User has no permissions to delete anything on post by id: " + id); // for testing debugging purposes
                return "error";
            }
        } else {
            System.err.println("Could not find a post by id: " + id); // for testing debugging purposes
            return "error";
        }
    }
}
