package com.programmer.blog.service;

import com.programmer.blog.Blog;
import com.programmer.programmer.Programmer;

/**
 * Created by kolyan on 9/23/15.
 */
public interface BlogService {

    void create(Blog blog);

    Blog read(Long id);

    Blog update(Blog blog);

    void remove(Blog blog);

    Blog getByProgrammer(Programmer programmer);

}
