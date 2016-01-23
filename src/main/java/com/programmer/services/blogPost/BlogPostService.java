package com.programmer.services.blogPost;

import com.programmer.entity.BlogPost;

/**
 * Created by kolyan on 9/23/15.
 */
public interface BlogPostService {

    BlogPost create(BlogPost blogPost);

    BlogPost read(Long id);

    BlogPost update(BlogPost blogPost);

    void delete(BlogPost blogPost);

    BlogPost getByBlogAndPostIds(Long blogId, Long postId);

}
