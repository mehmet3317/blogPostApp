package com.example.blogPostApp.controller;

import com.example.blogPostApp.model.Account;
import com.example.blogPostApp.model.Post;
import com.example.blogPostApp.service.AccountService;
import com.example.blogPostApp.service.PostService;
import com.example.blogPostApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private AccountService accountService;

  @GetMapping("/posts/{id}")
  public String getPost(@PathVariable Long id, Model model) {
      Optional<Post> optionalPost = postService.getById(id);
      if (optionalPost.isPresent()) {
          Post post = optionalPost.get();
          model.addAttribute("post", post);
          return "post";
      } else {
          return "post1";
      }
  }

    @GetMapping("/posts/new")
    public String createNewPost(Model model){
        Optional<Account> optionalAccount = accountService.findByEmail("user@email.com");
        if(optionalAccount.isPresent()){
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post",post);

            return "post_new.html";
        }else{
            return "post1";
        }
    }
    @PostMapping("/posts/new")
    public String saveNewPost(@ModelAttribute Post post){
        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }

}


