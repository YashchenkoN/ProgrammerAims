package com.programmer.aim.builder;

import com.programmer.aim.Aim;
import com.programmer.aim.AimForm;
import com.programmer.aim.service.AimService;
import com.programmer.programmer.Programmer;
import com.programmer.programmer.service.ProgrammerService;
import com.programmer.step.Step;
import com.programmer.step.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by kolyan on 8/30/15.
 */
@Component
public class AimBuilder {

    @Autowired
    private AimService aimService;

    @Autowired
    private StepService stepService;

    @Autowired
    private ProgrammerService programmerService;

    @Transactional
    public Aim buildAim(AimForm aimForm) {
        Programmer programmer = programmerService.getLoggedProgrammer();
        Aim aim = aimForm.createAim();
        aim.setProgrammer(programmer);
        Set<Step> steps = aim.getSteps();
        for(Step step : steps) {
            step.setAim(aim);
            if(step.getId() != null)
                stepService.update(step);
            else
                stepService.add(step);
        }
        if(aim.getId() != null)
            aimService.update(aim);
        else
            aimService.add(aim);
        programmer.addAim(aim);
        programmerService.update(programmer);
        return aim;
    }
}
