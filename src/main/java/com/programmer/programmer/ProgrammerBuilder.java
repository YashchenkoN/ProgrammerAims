package com.programmer.programmer;

import com.programmer.programmer.service.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

/**
 * Created by kolyan on 10/13/15.
 */
@Service
@Transactional
public class ProgrammerBuilder {

    @Autowired
    private ProgrammerService programmerService;

    public Programmer build(ProgrammerSignupForm programmerForm, Programmer programmer) {
        if(programmer == null) {
            programmer = new Programmer();
            programmer = programmerService.create(programmer);
        }
        programmer.setName(programmerForm.getName());
        programmer.setEmail(programmerForm.getEmail());
        programmer.setRegistrationDate(Calendar.getInstance());
        programmer.setPassword(programmerForm.getPassword());
        programmer = programmerService.update(programmer);
        return programmer;
    }
}
