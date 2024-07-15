package com.example.test0715.day0715;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private List<Post> posts = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public String List(Model model) {
        model.addAttribute("posts", posts);
        return "post/list";
    }

    @GetMapping("/new")
    public String newPostForm(Model model) {
    model.addAttribute("posts", new Post());
        return "post/form";
    }

    @PostMapping
    public String savePost(@ModelAttribute Post post){
        post.setId(nextId++);
        post.setCreateAt(LocalDateTime.now());
        posts.add(post);
        return "redirect:/posts";
    }
}
