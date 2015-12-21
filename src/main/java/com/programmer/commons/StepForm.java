package com.programmer.commons;

import com.programmer.entity.Difficult;
import com.programmer.entity.Step;

/**
 * Created by kolyan on 8/24/15.
 */
public class StepForm {
    private Long id;
    private Difficult difficult;
    private String specification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return new Step(id, difficult, specification);
    }

}
