package com.programmer.blog.service;

import com.programmer.blog.Blog;
import com.programmer.blog.dao.BlogDao;
import com.programmer.programmer.Programmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by kolyan on 9/23/15.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Transactional
    @Override
    public void create(Blog blog) {
        blogDao.create(blog);
    }

    @Transactional(readOnly = true)
    @Override
    public Blog read(Long id) {
        return blogDao.read(id);
    }

    @Transactional
    @Override
    public Blog update(Blog blog) {
        return blogDao.update(blog);
    }

    @Transactional
    @Override
    public void remove(Blog blog) {
        blogDao.delete(blog);
    }

    @Transactional
    @Override
    public Blog getByProgrammer(Programmer programmer) {
        return blogDao.getByProgrammer(programmer);
    }

}
