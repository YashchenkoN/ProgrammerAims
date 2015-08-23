package com.programmer.aim.service;

import com.programmer.aim.Aim;
import com.programmer.aim.dao.AimDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
@Service
public class AimServiceImpl implements AimService {

    @Autowired
    private AimDao aimDao;

    @Transactional(readOnly = true)
    @Override
    public Aim findByName(String name) {
        return (Aim) aimDao.getSession().createCriteria(Aim.class)
                .add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Transactional(readOnly = true)
    @Override
    public Aim findById(Long id) {
        return aimDao.get(id);
    }

    @Transactional
    @Override
    public void add(Aim aim) {
        aimDao.add(aim);
    }

    @Transactional
    @Override
    public Aim update(Aim aim) {
        return aimDao.update(aim);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Aim> getListOfProgrammerAims(Long id) {
        return aimDao.getSession().createCriteria(Aim.class)
                .add(Restrictions.eq("programmer_id", id)).list();
    }
}
