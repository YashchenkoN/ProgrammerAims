package com.programmer.step.dao;

import com.programmer.aim.Aim;
import com.programmer.step.Step;
import org.hibernate.Session;

/**
 * Created by kolyan on 8/11/15.
 */
public interface StepDao {

    public Session getSession();

    public void add(Step step);

    public Step update(Step step);

    public Step getById(Long id);

    public void delete(Step step);

}
