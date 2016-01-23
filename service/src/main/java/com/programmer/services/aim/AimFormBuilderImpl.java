package com.programmer.services.aim;

import com.programmer.api.StepForm;
import com.programmer.api.aim.AimForm;
import com.programmer.entity.Aim;
import com.programmer.entity.Step;
import com.programmer.services.step.StepFormBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        Aim aim = aimService.read(id);
        aimForm.setId(id);
        aimForm.setName(aim.getName());
        aimForm.setDescription(aim.getDescription());
        aimForm.setPriority(aim.getPriority());
        List<StepForm> stepForms = new ArrayList<>();
        for(Step step : aim.getSteps()) {
            stepForms.add(stepFormBuilder.buildStepForm(step));
        }
        aimForm.setSteps(stepForms);
        return aimForm;
    }

}
