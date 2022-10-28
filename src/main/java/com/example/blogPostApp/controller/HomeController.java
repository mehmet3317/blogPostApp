package com.example.blogPostApp.controller;

import com.example.blogPostApp.model.Post;
import com.example.blogPostApp.model.Userr;
import com.example.blogPostApp.service.PostService;
import com.example.blogPostApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;
    @RequestMapping(path = {"/","/search"})
    public String home(Post post, Model model, String keyword){
        int a = 0;
        if(keyword!=null) {

            List<Post> list = postService.getByKeyword(keyword);
            model.addAttribute("list", list);
            a = 1;
            model.addAttribute("a", a);
        }
        return findPaginated(model, 1,"createdAt","desc");
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(Model model,
                                @PathVariable(value = "pageNo") int pageNo,
                                @Param("sortField") String sortField,
                                @Param("sortDir") String sortDir) {
        int pageSize = 6;

        Page<Post> page = postService.findPaginated(pageNo,pageSize,sortField, sortDir);
        List<Post> listPosts = page.getContent();

        List<Userr> users = userService.getAllUsers();
        model.addAttribute("users", users);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listPosts", listPosts);

        return "homePage";
    }


}
