package com.programmer.dao;


import com.programmer.entity.Step;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
public interface StepDao {

    void create(Step step);

    Step update(Step step);

    Step read(Long id);

    void delete(Step step);

    List<Step> getListOfAimById(Long id);
}
