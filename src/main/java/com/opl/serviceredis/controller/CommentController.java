package com.opl.serviceredis.controller;

import com.opl.serviceredis.entity.Comment;
import com.opl.serviceredis.service.CommentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

   private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/comments")
    public List<Comment> getAllComment(){
        return commentService.getAllComments();
    }
}
