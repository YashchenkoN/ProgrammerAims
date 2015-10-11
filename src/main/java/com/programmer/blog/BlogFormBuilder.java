package com.programmer.blog;

import com.programmer.blog.service.BlogService;
import com.programmer.blogPost.BlogPostForm;
import com.programmer.blogPost.BlogPostFormBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by kolyan on 10/10/15.
 */
@Service
@Transactional
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
