package com.example.blogPostApp.controller;

import com.example.blogPostApp.model.Comment;
import com.example.blogPostApp.model.Post;
import com.example.blogPostApp.model.Userr;
import com.example.blogPostApp.service.CommentService;
import com.example.blogPostApp.service.PostService;
import com.example.blogPostApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.security.Principal;
import java.util.Optional;

@Controller
@SessionAttributes("comment")
public class CommentController {

    private final PostService postService;
    private final UserService blogUserService;
    private final CommentService commentService;

    @Autowired
    public CommentController(PostService postService, UserService blogUserService, CommentService commentService) {
        this.postService = postService;
        this.blogUserService = blogUserService;
        this.commentService = commentService;
    }
    @Secured("ROLE_USER")
    @GetMapping("/comment/{id}")
    public String showComment(@PathVariable Long id, Model model, Principal principal) {
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
//        // get username of current logged in session user
//        String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Userr> optionalBlogUser = this.blogUserService.findByEmail(authUsername);
        // find post by id
        Optional<Post> postOptional = this.postService.getById(id);
        // if both optionals is present set user and post to a new comment and put former in the model
        if (postOptional.isPresent() && optionalBlogUser.isPresent()) {
            Comment comment = new Comment();
            comment.setPost(postOptional.get());
            comment.setUser(optionalBlogUser.get());
            model.addAttribute("comment", comment);
            System.err.println("GET comment/{id}: " + comment + "/" + id); // for testing debugging purposes
            return "commentForm";
        } else {
            System.err.println("Could not find a post by id: " + id + " or user by logged in username: " + authUsername); // for testing debugging purposes
            return "error";
        }
    }

    @Secured("ROLE_USER")
    @PostMapping("/comment")
    public String validateComment(@ModelAttribute Comment comment, BindingResult bindingResult, SessionStatus sessionStatus) {
        System.err.println("POST comment: " + comment); // for testing debugging purposes
        if (bindingResult.hasErrors()) {
            System.err.println("Comment did not validate");
            return "commentForm";
        } else {
            this.commentService.save(comment);
            System.err.println("SAVE comment: " + comment); // for testing debugging purposes
            sessionStatus.setComplete();
            return "redirect:/posts/" + comment.getPost().getId();
        }
    }

    @Secured("ROLE_USER")
    @GetMapping("/comment/{id}/delete")
    public String deleteComment(@PathVariable Long id, Principal principal){

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        Optional<Comment> optionalComment = this.commentService.findById(id);
        Optional<Post> optionalPost = this.postService.getById(id);

        // Check if current logged in user is an owner and so has the right for modifications to happen
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            // Check if current logged in user is owner
            if (authUsername.equals(comment.getUser().getEmail())) {
                // if so then it is safe to remove post from database
                this.commentService.delete(comment);
                System.err.println("DELETED post: " + comment); // for testing debugging purposes
                return "redirect:/posts/" + comment.getPost().getId();}
            else {
                System.err.println("Current User has no permissions to edit anything on post by id: " + id); // for testing debugging purposes
                return "error";
                }
            } else {
            System.err.println("Could not find a post by id: " + id); // for testing debugging purposes
            return "error";
        }
    }
}
