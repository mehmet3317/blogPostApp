package com.example.blogPostApp.config;

import com.example.blogPostApp.model.Authority;
import com.example.blogPostApp.model.Post;
import com.example.blogPostApp.model.Userr;
import com.example.blogPostApp.repository.AuthorityRepository;
import com.example.blogPostApp.repository.UserRepository;
import com.example.blogPostApp.service.PostService;
import com.example.blogPostApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {



//        List<Post> posts = postService.getAllPosts();
//        Authority user = new Authority();
//        user.setName("ROLE_USER");
//        authorityRepository.save(user);
//        Userr user1 = new Userr();
//
//        user1.setFirstName("user_first");
//        user1.setLastName("user_last");
//        user1.setEmail("user2.user@domain.com");
//        user1.setPassword("1234");
//        Set<Authority> authorities1 = new HashSet<>();
//        authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
//        user1.setAuthorities(authorities1);
//        accountService.save(user1);



      /* if (posts.size() == 0) {
            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Userr user1 = new Userr();


            user1.setFirstName("user_first");
            user1.setLastName("user_last");
            user1.setEmail("user.user@domain.com");
            user1.setPassword("password");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            user1.setAuthorities(authorities1);



            Set<Authority> authorities2 = new HashSet<>();

            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);

            accountService.save(user1);

            Post post1 = new Post();
            post1.setTitle("What is Lorem Ipsum?");
            post1.setBody("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            post1.setUser(user1);



            postService.save(post1);

        }*/
    }
}
