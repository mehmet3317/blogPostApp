package com.example.blogPostApp.service;
import com.example.blogPostApp.model.Post;
import com.example.blogPostApp.model.Userr;
import com.example.blogPostApp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService  {

    @Autowired
    private PostRepository postRepository;


    public List<Post> getByKeyword(String keyword){
        return postRepository.findByKeyword(keyword);
    }
    public Page<Post> findPaginated(int pageNo, int pageSize,String sortField, String sortDir){
        Pageable pageable = PageRequest.of(pageNo - 1,pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        return this.postRepository.findAll(pageable);
    }
    public Optional<Post> getById(Long id) {

        return postRepository.findById(id);
    }
    public List<Post> getAllPosts() {
        //TODO by default it will be listed from most recent ones
        List<Post> posts;
        posts = postRepository.findAll();
        return posts;
    }

    public Post save(Post post) {

        if (post.getId() == null) {
            post.setCreatedAt(LocalDateTime.now());
        }
       // post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }
    public List<Post> getPostsByUser(Userr user) {

        List<Post> posts = new ArrayList<>();
        postRepository.getAllByUser(user)
                .forEach(posts::add);
        return posts;

    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

}