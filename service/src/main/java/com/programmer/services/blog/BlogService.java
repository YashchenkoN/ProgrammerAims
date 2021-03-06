package com.programmer.services.blog;

import com.programmer.entity.Blog;
import com.programmer.entity.Programmer;

/**
 * Created by kolyan on 9/23/15.
 */
public interface BlogService {

    Blog create(Blog blog);

    Blog read(Long id);

    Blog update(Blog blog);

    void remove(Blog blog);

    Blog getByProgrammer(Programmer programmer);

    Blog getLoggedProgrammerBlog();

}
