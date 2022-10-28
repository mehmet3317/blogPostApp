package com.example.blogPostApp.repository;

import com.example.blogPostApp.model.Post;
import com.example.blogPostApp.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select * from post s where s.body like %:keyword% or s.title like %:keyword%", nativeQuery = true)
    List<Post> findByKeyword(@Param("keyword") String keyword);

    public List<Post> getAllByUser(Userr user);
}

