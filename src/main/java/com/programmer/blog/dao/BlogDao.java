package com.programmer.blog.dao;

import com.programmer.blog.Blog;
import com.programmer.programmer.Programmer;
import org.springframework.stereotype.Repository;

/**
 * Created by kolyan on 9/23/15.
 */
public interface BlogDao {

    void create(Blog blog);

    Blog read(Long id);

    Blog update(Blog blog);

    void delete(Blog blog);

    Blog getByProgrammer(Programmer programmer);

}
