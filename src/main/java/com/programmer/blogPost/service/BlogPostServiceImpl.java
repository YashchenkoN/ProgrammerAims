package com.programmer.blogPost.service;

import com.programmer.blogPost.BlogPost;
import com.programmer.blogPost.dao.BlogPostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kolyan on 9/23/15.
 */
@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogPostDao blogPostDao;

    @Transactional
    @Override
    public void create(BlogPost blogPost) {
        blogPostDao.create(blogPost);
    }

    @Transactional(readOnly = true)
    @Override
    public BlogPost read(Long id) {
        return blogPostDao.read(id);
    }

    @Transactional
    @Override
    public BlogPost update(BlogPost blogPost) {
        return blogPostDao.update(blogPost);
    }

    @Transactional
    @Override
    public void delete(BlogPost blogPost) {
        blogPostDao.delete(blogPost);
    }

    @Transactional(readOnly = true)
    @Override
    public BlogPost getByBlogAndPostIds(Long blogId, Long postId) {
        return blogPostDao.getByBlogAndPostIds(blogId, postId);
    }
}
