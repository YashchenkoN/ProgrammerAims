package com.programmer.services.blog;

import com.programmer.api.blog.BlogForm;
import com.programmer.api.blog.BlogPostForm;
import com.programmer.entity.Blog;
import com.programmer.services.blogPost.BlogPostFormBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by kolyan on 10/10/15.
 */
@Service
public class BlogFormBuilder {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogPostFormBuilder blogPostFormBuilder;

    public BlogForm buildForm(Long blogId) {
        BlogForm blogForm = new BlogForm();
        Blog blog = blogService.read(blogId);
        if(blog == null) {
            return null;
        }
        blogForm.setId(blog.getId());
        blogForm.setBlogName(blog.getBlogName());
        blogForm.setOwnerId(blog.getOwner().getId());
        List<BlogPostForm> blogPostForms = blog.getPosts().stream()
                .filter(Objects::nonNull)
                .map(blogPostFormBuilder::build)
                .collect(Collectors.toList());
        blogForm.setPosts(blogPostForms);
        return blogForm;
    }
}
