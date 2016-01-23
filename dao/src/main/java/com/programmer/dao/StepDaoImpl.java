package com.programmer.dao;

import com.programmer.entity.Step;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
@Repository
public class StepDaoImpl implements StepDao {

    @Autowired
    private MainDao mainDao;

    @Override
    public void create(Step step) {
        mainDao.persist(step);
    }

    @Override
    public Step update(Step step) {
        return mainDao.merge(step);
    }

    @Override
    public Step read(Long id) {
        return mainDao.findEntity(Step.class, id);
    }

    @Override
    public void delete(Step step) {
        mainDao.remove(step);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Step> getListOfAimById(Long id) {
        return mainDao.getSession()
                .createCriteria(Step.class)
                .add(Restrictions.eq("aim_id", id))
                .list();
    }

}
