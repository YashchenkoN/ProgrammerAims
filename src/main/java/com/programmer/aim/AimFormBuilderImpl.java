package com.programmer.aim;

import com.programmer.aim.service.AimService;
import com.programmer.step.Step;
import com.programmer.step.StepForm;
import com.programmer.step.StepFormBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kolyan on 9/6/15.
 */
@Service
public class AimFormBuilderImpl implements AimFormBuilder {

    @Autowired
    private AimService aimService;

    @Autowired
    private StepFormBuilder stepFormBuilder;

    @Override
    public AimForm buildAimForm(Long id) {
        AimForm aimForm = new AimForm();
        Aim aim = aimService.findById(id);
        aimForm.setAimId(id);
        aimForm.setName(aim.getName());
        aimForm.setDescription(aim.getDescription());
        aimForm.setPriority(aim.getPriority());
        List<StepForm> stepForms = new ArrayList<>();
        for(Step step : aim.getSteps()) {
            stepForms.add(stepFormBuilder.buildStepForm(step));
        }
        aimForm.setStepForms(stepForms);
        return aimForm;
    }

}
