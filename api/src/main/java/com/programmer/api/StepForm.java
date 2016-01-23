package com.programmer.api;


/**
 * Created by kolyan on 8/24/15.
 */
public class StepForm {
    private Long id;
    private String difficult;
    private String specification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

}
