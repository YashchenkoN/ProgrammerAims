package com.programmer.blog;

import com.programmer.blog.service.BlogService;
import com.programmer.blogPost.BlogPost;
import com.programmer.blogPost.BlogPostBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by kolyan on 10/11/15.
 */
@Service
@Transactional
public class BlogBuilder {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogPostBuilder blogPostBuilder;

    public Blog build(BlogForm blogForm) {
        Blog blog = blogService.read(blogForm.getId());
        if(blog == null) {
            blog = new Blog();
            blog = blogService.create(blog);
        }
        blog.setBlogName(blogForm.getBlogName());
        List<BlogPost> blogPosts = blogForm.getPosts().stream()
                .filter(Objects::nonNull)
                .map(blogPostBuilder::build)
                .collect(Collectors.toList());
        blog.setPosts(blogPosts);
        blog = blogService.update(blog);
        return blog;
    }
}
