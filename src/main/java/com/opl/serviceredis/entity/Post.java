package com.opl.serviceredis.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    private String title;
    
    private String body;

    @Column(name = "created_at")
    private Date createdAt;
}
