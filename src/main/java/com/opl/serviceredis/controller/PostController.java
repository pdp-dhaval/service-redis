package com.opl.serviceredis.controller;

import com.opl.serviceredis.entity.Post;
import com.opl.serviceredis.service.RedisCacheService;
import com.opl.serviceredis.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{id}")
    public Post getPostById(@PathVariable("id") Integer id) {
        return postService.getPostById(id);
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postService.getAllPots();
    }


}
