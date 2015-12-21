package com.programmer.services.step;

import com.programmer.commons.StepForm;
import com.programmer.entity.Step;
import org.springframework.stereotype.Service;

/**
 * Created by kolyan on 9/6/15.
 */
@Service
public class StepFormBuiderImpl implements StepFormBuilder {

    @Override
    public StepForm buildStepForm(Step step) {
        StepForm stepForm = new StepForm();
        stepForm.setId(step.getId());
        stepForm.setDifficult(step.getDifficult());
        stepForm.setSpecification(step.getSpecification());
        return stepForm;
    }
}
