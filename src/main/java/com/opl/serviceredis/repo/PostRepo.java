package com.opl.serviceredis.repo;

import com.opl.serviceredis.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Integer> {
}
