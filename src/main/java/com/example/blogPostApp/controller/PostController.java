package com.example.blogPostApp.controller;

import com.example.blogPostApp.model.Post;
import com.example.blogPostApp.model.Userr;
import com.example.blogPostApp.service.PostService;
import com.example.blogPostApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
  private UserService userService;

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
        Optional<Userr> optionalUserr= userService.findUserByEmail("admin@email.com");
        if(optionalUserr.isPresent()){
            Post post = new Post();
            post.setUser(optionalUserr.get());
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


