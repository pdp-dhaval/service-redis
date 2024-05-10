package com.opl.serviceredis.service;

import com.opl.serviceredis.entity.Comment;
import com.opl.serviceredis.repo.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepo repo;
    private final RedisCacheService redisCacheService;

    public CommentService(CommentRepo repo, RedisCacheService redisCacheService) {
        this.repo = repo;
        this.redisCacheService = redisCacheService;
    }

    public List<Comment> getAllComments() {
        String key = "comment:all";
        if (redisCacheService.keyExists(key)) {
            return redisCacheService.getList(key, Comment.class);
        } else {
            List<Comment> commentList = repo.findAll();
            redisCacheService.set(key, commentList, 60L);
            return commentList;
        }
    }
}
