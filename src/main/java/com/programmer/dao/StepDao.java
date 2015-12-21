package com.programmer.dao;

import com.programmer.entity.Step;
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
