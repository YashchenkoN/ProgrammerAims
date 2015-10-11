package com.programmer.blog;

import com.programmer.blogPost.BlogPost;
import com.programmer.programmer.Programmer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolyan on 9/23/15.
 */
@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @NotNull
    private Long id;

    @Column(name = "blog_name", unique = true)
    @NotNull
    private String blogName;

    @OneToOne
    @JoinColumn(name = "owner")
    private Programmer owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<BlogPost> posts;

    public Blog() {
        posts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public Programmer getOwner() {
        return owner;
    }

    public void setOwner(Programmer owner) {
        this.owner = owner;
    }

    public List<BlogPost> getPosts() {
        return posts;
    }

    public void setPosts(List<BlogPost> posts) {
        this.posts = posts;
    }
}
