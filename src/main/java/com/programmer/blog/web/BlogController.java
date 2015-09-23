package com.programmer.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kolyan on 9/23/15.
 */
@Controller(value = "blog")
public class BlogController {

    @RequestMapping(value = "/{id}")
    public ModelAndView blogById(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("/blog/blog");
        return mav;
    }
}
