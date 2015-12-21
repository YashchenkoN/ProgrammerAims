package com.programmer.dao;

import com.programmer.MainDao;
import com.programmer.entity.Aim;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kolyan on 8/11/15.
 */
@Repository
public class AimDaoImpl implements AimDao {

    @Autowired
    private MainDao mainDao;

    @Override
    public Session getSession() {
        return mainDao.getSession();
    }

    @Override
    public void add(Aim aim) {
        mainDao.persist(aim);
    }

    @Override
    public Aim update(Aim aim) {
        return mainDao.merge(aim);
    }

    @Override
    public Aim get(Long id) {
        return mainDao.findEntity(Aim.class, id);
    }

    @Override
    public void delete(Aim aim) {
        mainDao.remove(aim);
    }

}
