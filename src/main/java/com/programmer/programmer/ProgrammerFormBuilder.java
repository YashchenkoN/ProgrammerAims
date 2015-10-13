package com.programmer.programmer;

import com.programmer.aim.Aim;
import com.programmer.aim.AimForm;
import com.programmer.aim.AimFormBuilder;
import com.programmer.blog.Blog;
import com.programmer.blog.BlogForm;
import com.programmer.blog.BlogFormBuilder;
import com.programmer.programmer.service.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by kolyan on 10/12/15.
 */
@Service
@Transactional
public class ProgrammerFormBuilder {

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private AimFormBuilder aimFormBuilder;

    @Autowired
    private BlogFormBuilder blogFormBuilder;

    public ProgrammerSignupForm build(Programmer programmer) {
        ProgrammerSignupForm programmerForm = new ProgrammerSignupForm();
        programmerForm.setEmail(programmer.getEmail());
        programmerForm.setName(programmer.getName());
        return programmerForm;
    }

    public ProgrammerForm build(Long id) {
        Programmer programmer = programmerService.findById(id);
        ProgrammerForm programmerForm = null;
        if(programmer != null) {
            programmerForm = new ProgrammerForm();
            programmerForm.setId(programmer.getId());
            programmerForm.setEmail(programmer.getEmail());
            programmerForm.setName(programmer.getName());
            programmerForm.setLastVisitDate(programmer.getLastVisit().toString());
            programmerForm.setRegistrationDate(programmer.getRegistrationDate().toString());
            List<AimForm> aimForms = programmer.getAims().stream()
                    .filter(Objects::nonNull)
                    .map(Aim::getId)
                    .map(aimFormBuilder::buildAimForm)
                    .collect(Collectors.toList());
            programmerForm.setAims(aimForms);
            List<BlogForm> blogForms = programmer.getBlogs().stream()
                    .filter(Objects::nonNull)
                    .map(Blog::getId)
                    .map(blogFormBuilder::buildForm)
                    .collect(Collectors.toList());
            programmerForm.setBlogs(blogForms);
        }
        return programmerForm;
    }
}
