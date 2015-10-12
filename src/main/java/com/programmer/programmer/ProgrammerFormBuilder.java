package com.programmer.programmer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kolyan on 10/12/15.
 */
@Service
@Transactional
public class ProgrammerFormBuilder {

    public ProgrammerForm build(Programmer programmer) {
        ProgrammerForm programmerForm = new ProgrammerForm();
        programmerForm.setEmail(programmer.getEmail());
        programmerForm.setName(programmer.getName());
        return programmerForm;
    }
}
