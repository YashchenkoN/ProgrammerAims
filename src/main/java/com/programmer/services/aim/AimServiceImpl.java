package com.programmer.services.aim;

import com.programmer.entity.Aim;
import com.programmer.dao.AimDao;
import com.programmer.entity.Programmer;
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
        return (Aim) aimDao.getSession()
                .createCriteria(Aim.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
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

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Aim> getListOfProgrammerAims(Programmer programmer) {
        return aimDao.getSession()
                .createCriteria(Aim.class)
                .add(Restrictions.eq("programmer", programmer))
                .list();
    }

    @Transactional
    @Override
    public void delete(Aim aim) {
        aimDao.delete(aim);
    }
}