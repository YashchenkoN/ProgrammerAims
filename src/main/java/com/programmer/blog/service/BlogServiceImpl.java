package com.programmer.blog.service;

import com.programmer.blog.Blog;
import com.programmer.blog.dao.BlogDao;
import com.programmer.entity.Programmer;
import com.programmer.services.programmer.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Created by kolyan on 9/23/15.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private ProgrammerService programmerService;

    @Transactional
    @Override
    public Blog create(Blog blog) {
        blogDao.create(blog);
        return blog;
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

    @Transactional(readOnly = true)
    @Override
    public Blog getByProgrammer(Programmer programmer) {
        return blogDao.getByProgrammer(programmer);
    }

    @Transactional(readOnly = true)
    @Override
    public Blog getLoggedProgrammerBlog() {
        Object principal = Optional.of(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .orElse(null);
        if(principal != null && principal instanceof User) {
            Programmer programmer = programmerService.findByEmail(((User) principal).getUsername());
            return getByProgrammer(programmer);
        }
        return null;
    }
}
