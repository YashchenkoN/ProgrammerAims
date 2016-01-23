package com.programmer.services.step;

import com.programmer.entity.Step;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
public interface StepService {

    Step read(Long id);

    void create(Step step);

    Step update(Step step);

    List<Step> getListOfAimById(Long id);

}
