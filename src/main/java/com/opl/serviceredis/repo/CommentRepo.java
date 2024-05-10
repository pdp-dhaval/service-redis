package com.opl.serviceredis.repo;

import com.opl.serviceredis.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
