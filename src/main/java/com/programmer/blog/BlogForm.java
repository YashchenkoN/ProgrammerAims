package com.programmer.blog;

import com.programmer.blogPost.BlogPostForm;

import java.util.List;

/**
 * Created by kolyan on 9/23/15.
 */
public class BlogForm {

    private Long id;

    private Long owner_id;

    private String blogName;

    private List<BlogPostForm> posts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public List<BlogPostForm> getPosts() {
        return posts;
    }

    public void setPosts(List<BlogPostForm> posts) {
        this.posts = posts;
    }

    public boolean addPost(BlogPostForm blogPostForm) {
        return posts.add(blogPostForm);
    }

    public boolean removePost(BlogPostForm blogPostForm) {
        return posts.remove(blogPostForm);
    }

}
