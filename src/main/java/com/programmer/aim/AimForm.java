package com.programmer.aim;

import com.programmer.step.Step;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
public class AimForm {

    @NotBlank
    private String description;

    @NotBlank
    private Long priority;

    private List<Step> steps;

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

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Aim createAim() {
        return new Aim(description, steps, priority);
    }
}
