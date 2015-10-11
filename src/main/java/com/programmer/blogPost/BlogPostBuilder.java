package com.programmer.blogPost;

import com.programmer.blogPost.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kolyan on 10/11/15.
 */
@Service
@Transactional
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
