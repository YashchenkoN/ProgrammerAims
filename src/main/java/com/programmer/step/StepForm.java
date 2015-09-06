package com.programmer.step;

import javax.validation.constraints.NotNull;

/**
 * Created by kolyan on 8/24/15.
 */
public class StepForm {

    private Long stepId;

    private Difficult difficult;

    private String specification;

    public Long getStepId() {
        return stepId;
    }

    public void setStepId(Long stepId) {
        this.stepId = stepId;
    }

    public Difficult getDifficult() {
        return difficult;
    }

    public void setDifficult(Difficult difficult) {
        this.difficult = difficult;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Step createStep() {
        return new Step(stepId, difficult, specification);
    }

}
