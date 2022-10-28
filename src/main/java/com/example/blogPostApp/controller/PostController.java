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
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/like/{id}")
    public String setLikes(@PathVariable Long id, Model model){

        Optional<Post> optionalPost = this.postService.getById(id);
        Post post = optionalPost.get();
        post.setLikes(post.getLikes()+1);
        model.addAttribute("like", post.getLikes());
        postService.save(post);

        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/blogHomeUser/{id}")
    public String seeAllPostsByUser(Model model,@PathVariable() Long id,Principal principal) {

        Optional<Userr> optionalUser = userService.getById(id);
        Userr user = optionalUser.get();


        List<Post> postList =postService.getPostsByUser(user);

//        List<Post> post = new ArrayList<>();
//        post.add(postList.get(0));
//
//        model.addAttribute("post",post);

        model.addAttribute("posts",postService.getPostsByUser(user));

        return "blogHomePage";
    }
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        // find post by id
        Optional<Post> optionalPost = this.postService.getById(id);

        // if post exists put it in model
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "postPage";
        } else {
            return "404";
        }
    }

    @GetMapping("/blogHomeUser/new/{email}")
    @PreAuthorize("isAuthenticated()")
    public String blogHomeUser(@PathVariable String email, Model model, Principal principal) {
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        Optional<Userr> optionalUserr2 = userService.findByEmail(email);
        Userr user = new Userr();
        if (optionalUserr2.isPresent()) {
            user = optionalUserr2.get();
        }
        Optional<Userr> optionalAccount = userService.findByEmail(authUsername);
        if (optionalAccount.isPresent() && authUsername.equals(user.getEmail())) {
            Post post = new Post();
            post.setUser(optionalAccount.get());
            if (authUsername.equals(post.getUser().getEmail())) {
                model.addAttribute("post", post);
                return "newPostForm";
            } else {
                System.err.println("Current User has no permissions to edit anything on post by id: "); // for testing debugging purposes
                return "error";
            }
        } else {
            return "error";

        }
    }
    @PostMapping("/posts/new")
    @PreAuthorize("isAuthenticated()")
    public String saveNewPost(@ModelAttribute Post post, Principal principal){
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        if (post.getUser().getEmail().compareToIgnoreCase(authUsername) < 0) {
            // TODO: some kind of error?
            // our account email on the Post not equal to current logged in account!
        }
        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable Long id, Model model,Principal principal) {
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        Optional<Post> optionalPost = this.postService.getById(id);
        // Check if current logged in user is an owner and so has the right for modifications to happen
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            // Check if current logged in user is owner
            if (authUsername.equals(post.getUser().getEmail())) {
                model.addAttribute("post", post);
                System.err.println("EDIT post: " + post); // for testing debugging purposes
                return "editPost";
            } else {
                System.err.println("Current User has no permissions to edit anything on post by id: " + id); // for testing debugging purposes
                return "error";

            }
        } else {
            System.err.println("Could not find a post by id: " + id); // for testing debugging purposes
            return "error";
        }
    }
    @PostMapping("/posts/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable Long id, Post post, BindingResult result, Model model) {

        Optional<Post> optionalPost = postService.getById(id);

        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();

            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());

            postService.save(existingPost);
        }
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{id}/delete")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String deletePost(@PathVariable Long id, Principal principal) {

        // Just curious  what if we get username from Principal instead of SecurityContext
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        Optional<Post> optionalPost = this.postService.getById(id);
        // Check if current logged in user is an owner and so has the right for modifications to happen
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            // Check if current logged in user is owner
            if (authUsername.equals(post.getUser().getEmail())) {
                // if so then it is safe to remove post from database
                this.postService.delete(post);
                System.err.println("DELETED post: " + post); // for testing debugging purposes
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

