package com.programmer.services.aim;

import com.programmer.entity.Aim;
import com.programmer.api.aim.AimForm;
import com.programmer.entity.Programmer;
import com.programmer.services.programmer.ProgrammerService;
import com.programmer.entity.Step;
import com.programmer.services.step.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<Step> steps = aim.getSteps();
        for(Step step : steps) {
            step.setAim(aim);
            if(step.getId() != null)
                stepService.update(step);
            else
                stepService.add(step);
        }
        if(aim.getId() != null) {
            aimService.update(aim);
        } else {
            aimService.add(aim);
        }
        programmerService.update(programmer);
        return aim;
    }
}
