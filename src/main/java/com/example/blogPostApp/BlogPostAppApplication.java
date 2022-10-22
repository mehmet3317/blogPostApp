package com.example.blogPostApp;

import com.example.blogPostApp.model.Post;
import com.example.blogPostApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BlogPostAppApplication{

	public static void main(String[] args) {
		SpringApplication.run(BlogPostAppApplication.class, args);
	}

	}

