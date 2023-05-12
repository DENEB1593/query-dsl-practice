package io.dev.deneb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "blog_post")
@Getter
@Setter
public class BlogPost {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String body;

    @ManyToOne
    private User user;
}
