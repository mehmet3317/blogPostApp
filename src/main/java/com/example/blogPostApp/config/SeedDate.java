package com.example.blogPostApp.config;

import com.example.blogPostApp.model.Account;
import com.example.blogPostApp.model.Post;
import com.example.blogPostApp.service.AccountService;
import com.example.blogPostApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


    @Component

    public class SeedDate implements CommandLineRunner {
        @Autowired
        private PostService postService;

        @Autowired
        private AccountService accountService;


        @Override
        public void run(String... args) throws Exception {
            List<Post> post = postService.getAllPosts();
            if(post.size() == 0){

                Account account1 = new Account();
                Account account2 = new Account();

                account1.setFirstName("user");
                account1.setLastName("user");
                account1.setEmail("user@email.com");
                account1.setPassword("password");
                accountService.save(account1);

                account2.setFirstName("admin");
                account2.setLastName("admin");
                account2.setEmail("admin@email.com");
                account2.setPassword("password");

                accountService.save(account2);
                Post post1 = new Post();
                post1.setTitle("title of post 1");
                post1.setBody("This is the body post 1");
                post1.setAccount(account1);

                Post post2 = new Post();
                post2.setTitle("title of post 2");
                post2.setBody("This is the body post 2");
                post2.setAccount(account2);


                postService.save(post1);
                postService.save(post2);
            }
        }
    }


