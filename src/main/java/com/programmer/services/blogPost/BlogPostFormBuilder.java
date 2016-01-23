package com.programmer.services.blogPost;

import com.programmer.entity.BlogPost;
import com.programmer.api.blog.BlogPostForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kolyan on 9/23/15.
 */
@Service
public class BlogPostFormBuilder {

    @Autowired
    private BlogPostService blogPostService;

    public BlogPostForm build(Long id) {
        BlogPost blogPost = blogPostService.read(id);
        if(blogPost == null) {
            blogPost = new BlogPost();
            blogPostService.create(blogPost);
        }
        return build(blogPost);
    }

    public BlogPostForm build(BlogPost blogPost) {
        BlogPostForm blogPostForm = new BlogPostForm();
        blogPostForm.setId(blogPost.getId());
        blogPostForm.setPostName(blogPost.getPostName());
        blogPostForm.setText(blogPost.getText());
        return blogPostForm;
    }
}
