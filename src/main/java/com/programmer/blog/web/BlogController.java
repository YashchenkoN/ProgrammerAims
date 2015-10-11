package com.programmer.blog.web;

import com.programmer.blog.Blog;
import com.programmer.blog.BlogBuilder;
import com.programmer.blog.BlogForm;
import com.programmer.blog.BlogFormBuilder;
import com.programmer.blog.service.BlogService;
import com.programmer.programmer.Programmer;
import com.programmer.programmer.service.ProgrammerService;
import com.programmer.support.web.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by kolyan on 9/23/15.
 */
@Controller(value = "blog")
public class BlogController {

    @Autowired
    private BlogFormBuilder blogFormBuilder;

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogBuilder blogBuilder;

    @Autowired
    private ProgrammerService programmerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String blogById(@PathVariable String id, RedirectAttributes ra, Model model) {
        Long blogId;
        try {
            blogId = Long.parseLong(id);
        } catch (Exception e) {
            MessageHelper.addErrorAttribute(ra, "unknown.error");
            return "redirect:/";
        }
        BlogForm blogForm = blogFormBuilder.buildForm(blogId);
        if(blogForm == null) {
            MessageHelper.addErrorAttribute(ra, "blog.notfound");
            return "redirect:/";
        }
        model.addAttribute("blog", blogForm);
        return "blog/blog";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ACTIVE')")
    public String addBlog(Model model) {
        Blog blog = blogService.getLoggedProgrammerBlog();
        if(blog == null) {
            BlogForm blogForm = new BlogForm();
            model.addAttribute("blog", blogForm);
        } else {
            return "redirect:/blog/edit";
        }
        return "blog/blog-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ACTIVE')")
    public String addBlog(@ModelAttribute BlogForm blogForm) {
        Blog blog = blogBuilder.build(blogForm);
        Programmer programmer = programmerService.getLoggedProgrammer();
        blog.setOwner(programmer);
        blogService.update(blog);
        return "redirect:/blog/" + blog.getId();
    }
}
