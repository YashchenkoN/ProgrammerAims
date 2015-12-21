package com.programmer.services.step;

import com.programmer.commons.StepForm;
import com.programmer.entity.Step;

/**
 * Created by kolyan on 9/6/15.
 */
public interface StepFormBuilder {

    StepForm buildStepForm(Step step);

}
