package com.programmer.blogPost;

import com.programmer.blogPost.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kolyan on 9/23/15.
 */
@Component
public class BlogPostFormBuilder {

    @Autowired
    private BlogPostService blogPostService;

    public BlogPostForm build(Long id) {
        BlogPost blogPost = blogPostService.read(id);
        BlogPostForm blogPostForm = new BlogPostForm();
        blogPostForm.setId(blogPost.getId());
        blogPostForm.setBlogId(blogPost.getBlog().getId());
        blogPostForm.setPostName(blogPost.getPostName());
        blogPostForm.setText(blogPost.getText());
        return blogPostForm;
    }
}
