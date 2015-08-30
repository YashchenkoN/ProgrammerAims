package com.programmer.aim;

import com.programmer.step.Step;
import com.programmer.step.StepForm;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
public class AimForm {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private Long priority;

    private List<StepForm> stepForms;

    public AimForm() {
        stepForms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public List<StepForm> getStepForms() {
        return stepForms;
    }

    public void setStepForms(List<StepForm> stepForms) {
        this.stepForms = stepForms;
    }

    public Aim createAim() {
        List<Step> steps = new ArrayList<>();
        for(StepForm stepForm : stepForms)
            steps.add(stepForm.createStep());
        return new Aim(name, description, steps, priority);
    }
}
