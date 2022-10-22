package com.example.blogPostApp.config;


import com.example.blogPostApp.model.Post;

import com.example.blogPostApp.model.Userr;
import com.example.blogPostApp.service.PostService;
import com.example.blogPostApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


 @Component

 public class SeedDate implements CommandLineRunner {
     @Autowired
     private PostService postService;

     @Autowired
     private UserService userService;


     @Override
     public void run(String... args) throws Exception {
         List<Post> post = postService.getAllPosts();
         if (post.size() == 0) {

             Userr user1 = new Userr();
             Userr user2 = new Userr();

             user1.setFirstName("user");
             user1.setLastName("user");
             user1.setUserName("user1");
             user1.setEmail("user@email.com");
             user1.setPassword("password");
             user1.setCity("city1");
             user1.setStreet("street1");
             user1.setHouseNr("0000");
             user1.setZipCode(1000);
             userService.save(user1);

             user2.setFirstName("admin");
             user2.setLastName("admin");
             user2.setUserName("admin");
             user2.setEmail("admin@email.com");
             user2.setPassword("password");
             user2.setCity("city2");
             user2.setStreet("street2");
             user2.setHouseNr("22L");
             user2.setZipCode(2000);
             userService.save(user2);
             Post post1 = new Post();
             post1.setTitle("title of post 1");
             post1.setBody("This is the body post 1");
             post1.setUser(user1);


             Post post2 = new Post();
             post2.setTitle("title of post 2");
             post2.setBody("This is the body post 2");
             post2.setUser(user2);



             postService.save(post1);
             postService.save(post2);
         }
     }

 }

