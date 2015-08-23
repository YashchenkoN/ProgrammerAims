package com.programmer.step.service;

import com.programmer.programmer.Programmer;
import com.programmer.step.Step;
import com.programmer.step.dao.StepDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
@Service
public class StepServiceImpl implements StepService {

    @Autowired
    private StepDao stepDao;

    @Transactional(readOnly = true)
    @Override
    public Step findById(Long id) {
        return stepDao.getById(id);
    }

    @Transactional
    @Override
    public void add(Step step) {
        stepDao.add(step);
    }

    @Transactional
    @Override
    public Step update(Step step) {
        return stepDao.update(step);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Step> getListOfAimById(Long id) {
        return stepDao.getSession().createCriteria(Step.class)
                .add(Restrictions.eq("aim_id", id)).list();
    }
}
