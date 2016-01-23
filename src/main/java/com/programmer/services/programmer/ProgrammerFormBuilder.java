package com.programmer.services.programmer;

import com.programmer.api.programmer.ProgrammerRequest;
import com.programmer.api.programmer.ProgrammerForm;
import com.programmer.entity.Aim;
import com.programmer.api.aim.AimForm;
import com.programmer.entity.Programmer;
import com.programmer.services.aim.AimFormBuilder;
import com.programmer.entity.Blog;
import com.programmer.api.blog.BlogForm;
import com.programmer.services.blog.BlogFormBuilder;
import com.programmer.services.aim.AimService;
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

    @Autowired
    private AimService aimService;

    public ProgrammerRequest build(Programmer programmer) {
        ProgrammerRequest programmerForm = new ProgrammerRequest();
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
            programmerForm.setRole(programmer.getRole().toString());
            programmerForm.setEmail(programmer.getEmail());
            programmerForm.setName(programmer.getName());
            programmerForm.setLastVisitDate(programmer.getLastVisit().toString());
            programmerForm.setRegistrationDate(programmer.getRegistrationDate().toString());
            List<AimForm> aimForms = aimService.getListOfProgrammerAims(programmer).stream()
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
