package com.programmer.services.blog;

import com.programmer.entity.Blog;
import com.programmer.api.blog.BlogForm;
import com.programmer.entity.BlogPost;
import com.programmer.services.blogPost.BlogPostBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by kolyan on 10/11/15.
 */
@Service
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
