package com.programmer.dao;

import com.programmer.entity.Blog;
import com.programmer.entity.Programmer;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kolyan on 9/23/15.
 */
@Repository
public class BlogDaoImpl implements BlogDao {

    @Autowired
    private MainDao mainDao;

    @Override
    public void create(Blog blog) {
        mainDao.persist(blog);
    }

    @Override
    public Blog read(Long id) {
        return mainDao.findEntity(Blog.class, id);
    }

    @Override
    public Blog update(Blog blog) {
        return mainDao.merge(blog);
    }

    @Override
    public void delete(Blog blog) {
        mainDao.remove(blog);
    }

    @Override
    public Blog getByProgrammer(Programmer programmer) {
        return (Blog) mainDao.getSession().createCriteria(Blog.class)
                .add(Restrictions.eq("owner", programmer))
                .uniqueResult();
    }
}
