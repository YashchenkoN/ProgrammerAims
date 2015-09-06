package com.programmer.step;

import com.programmer.step.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kolyan on 9/6/15.
 */
@Service
public class StepFormBuiderImpl implements StepFormBuilder {

    @Override
    public StepForm buildStepForm(Step step) {
        StepForm stepForm = new StepForm();
        stepForm.setStepId(step.getId());
        stepForm.setDifficult(step.getDifficult());
        stepForm.setSpecification(step.getSpecification());
        return stepForm;
    }
}
