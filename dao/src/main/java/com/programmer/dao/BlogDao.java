package com.programmer.dao;


import com.programmer.entity.Blog;
import com.programmer.entity.Programmer;

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
