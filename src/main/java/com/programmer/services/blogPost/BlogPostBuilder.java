package com.programmer.services.blogPost;

import com.programmer.entity.BlogPost;
import com.programmer.api.blog.BlogPostForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kolyan on 10/11/15.
 */
@Service
public class BlogPostBuilder {

    @Autowired
    private BlogPostService blogPostService;

    public BlogPost build(BlogPostForm blogPostForm) {
        BlogPost blogPost = blogPostService.read(blogPostForm.getId());
        if(blogPost == null) {
            blogPost = new BlogPost();
            blogPost = blogPostService.create(blogPost);
        }
        blogPost.setPostName(blogPostForm.getPostName());
        blogPost.setText(blogPostForm.getText());
        blogPostService.update(blogPost);
        return blogPost;
    }
}
