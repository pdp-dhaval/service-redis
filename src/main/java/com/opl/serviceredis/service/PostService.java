package com.opl.serviceredis.service;

import com.opl.serviceredis.entity.Post;
import com.opl.serviceredis.repo.PostRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepo repo;
    private final RedisCacheService redisCacheService;

    public PostService(PostRepo repo, RedisCacheService redisCacheService) {
        this.repo = repo;
        this.redisCacheService = redisCacheService;
    }

    public Post getPostById(Integer id) {
        String key = "post:" + id;
        if (redisCacheService.keyExists(key)) {
            return redisCacheService.get(key, Post.class);
        } else {
            Post post = repo.findById(id).orElse(null);
            if (post!=null){
                redisCacheService.set(key,post, 300L);
            }
            return post;
        }
    }

    public List<Post> getAllPots(){
        String key = "post:all";
        if (redisCacheService.keyExists(key)) {
            return redisCacheService.getList(key, Post.class);
        }else{
            List<Post> postList = repo.findAll();
            redisCacheService.set(key,postList,60L);
            return postList;
        }
    }

}
