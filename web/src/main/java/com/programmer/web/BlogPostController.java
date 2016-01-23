package com.programmer.web;

import com.programmer.api.blog.BlogPostForm;
import com.programmer.entity.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.programmer.services.blogPost.BlogPostFormBuilder;
import com.programmer.services.blogPost.BlogPostService;
import com.programmer.utils.MessageHelper;

/**
 * Created by kolyan on 9/23/15.
 */
@Controller
@RequestMapping(value = "blog")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private BlogPostFormBuilder blogPostFormBuilder;

    @RequestMapping(value = "/{blogId}/{postId}", method = RequestMethod.GET)
    public ModelAndView readPost(@PathVariable("blogId") String blogId,
                                 @PathVariable("postId") String postId,
                                 RedirectAttributes ra) {
        Long longBlogId, longPostId;
        try {
            longBlogId = Long.parseLong(blogId);
            longPostId = Long.parseLong(postId);
        } catch (NumberFormatException e) {
            MessageHelper.addErrorAttribute(ra, "post.notfound");
            return new ModelAndView("redirect:/");
        }
        ModelAndView mav = new ModelAndView("blog/blog-post");
        BlogPost blogPost = blogPostService.getByBlogAndPostIds(longBlogId, longPostId);
        if(blogPost != null) {
            BlogPostForm blogPostForm = blogPostFormBuilder.build(blogPost.getId());
            mav.addObject("blogPostForm", blogPostForm);
        } else {
            MessageHelper.addErrorAttribute(ra, "post.notfound");
            return new ModelAndView("redirect:/");
        }
        return mav;
    }

    @RequestMapping(value = "/postAdd", method = RequestMethod.GET)
    public ModelAndView addPost() {
        ModelAndView mav = new ModelAndView("blog/blog-post-add");
        mav.addObject("blogPostForm", new BlogPostForm());
        return mav;
    }
}
