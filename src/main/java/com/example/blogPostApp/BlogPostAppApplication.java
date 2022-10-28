package com.example.blogPostApp;

import com.example.blogPostApp.model.Post;
import com.example.blogPostApp.repository.PostRepository;
import com.example.blogPostApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BlogPostAppApplication implements CommandLineRunner {

	@Autowired
	private PostService postService;



	public static void main(String[] args) {
		SpringApplication.run(BlogPostAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//	List<Post> list = (List<Post>) postService.findPaginatedByLikes(1,6);
//		System.out.println(list);


	}

}
