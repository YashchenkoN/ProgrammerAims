package com.programmer.services.step;
import com.programmer.dao.StepDao;
import com.programmer.entity.Step;
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
    public Step read(Long id) {
        return stepDao.read(id);
    }

    @Transactional
    @Override
    public void create(Step step) {
        stepDao.create(step);
    }

    @Transactional
    @Override
    public Step update(Step step) {
        return stepDao.update(step);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Step> getListOfAimById(Long id) {
        return stepDao.getListOfAimById(id);
    }
}
