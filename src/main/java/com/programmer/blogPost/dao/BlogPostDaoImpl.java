package com.programmer.blogPost.dao;

import com.programmer.MainDao;
import com.programmer.blogPost.BlogPost;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kolyan on 9/23/15.
 */
@Repository
public class BlogPostDaoImpl implements BlogPostDao {

    @Autowired
    private MainDao mainDao;

    @Override
    public void create(BlogPost blogPost) {
        mainDao.persist(blogPost);
    }

    @Override
    public BlogPost read(Long id) {
        return mainDao.findEntity(BlogPost.class, id);
    }

    @Override
    public BlogPost update(BlogPost blogPost) {
        return mainDao.merge(blogPost);
    }

    @Override
    public void delete(BlogPost blogPost) {
        mainDao.remove(blogPost);
    }

    @Override
    public BlogPost getByBlogAndPostIds(Long blogId, Long postId) {
        return (BlogPost) mainDao.getSession().createCriteria(BlogPost.class)
                .add(Restrictions.eq("blog", blogId))
                .add(Restrictions.eq("blog_post", postId))
                .uniqueResult();
    }
}
