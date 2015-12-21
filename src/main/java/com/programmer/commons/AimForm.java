package com.programmer.commons;

import com.programmer.entity.Aim;
import com.programmer.entity.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
public class AimForm {

    private Long id;
    private String name;
    private String description;
    private Long priority;
    private List<StepForm> steps;

    public AimForm() {
        steps = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<StepForm> getSteps() {
        return steps;
    }

    public void setSteps(List<StepForm> steps) {
        this.steps = steps;
    }

    public Aim createAim() {
        List<Step> steps = new ArrayList<>();
        for(StepForm stepForm : this.steps)
            steps.add(stepForm.createStep());
        return new Aim(id, name, description, steps, priority);
    }
}
