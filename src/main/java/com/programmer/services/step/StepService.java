package com.programmer.services.step;

import com.programmer.entity.Step;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
public interface StepService {

    public Step findById(Long id);

    public void add(Step step);

    public Step update(Step step);

    public List<Step> getListOfAimById(Long id);

}
