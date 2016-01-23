package com.programmer.dao;

import com.programmer.entity.Step;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kolyan on 8/11/15.
 */
@Repository
public class StepDaoImpl implements StepDao {

    @Autowired
    private MainDao mainDao;

    @Override
    public Session getSession() {
        return mainDao.getSession();
    }

    @Override
    public void add(Step step) {
        mainDao.persist(step);
    }

    @Override
    public Step update(Step step) {
        return mainDao.merge(step);
    }

    @Override
    public Step getById(Long id) {
        return mainDao.findEntity(Step.class, id);
    }

    @Override
    public void delete(Step step) {
        mainDao.remove(step);
    }

}
