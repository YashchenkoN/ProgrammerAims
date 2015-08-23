package com.programmer.programmer.dao;

import com.programmer.MainDao;
import com.programmer.programmer.Programmer;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kolyan on 8/11/15.
 */

@Repository
public class ProgrammerDaoImpl implements ProgrammerDao {

    @Autowired
    private MainDao mainDao;

    @Override
    public Session getSession() {
        return mainDao.getSession();
    }

    @Override
    public void add(Programmer programmer) {
        mainDao.persist(programmer);
    }

    @Override
    public Programmer update(Programmer programmer) {
        return mainDao.merge(programmer);
    }

    @Override
    public Programmer get(Long id) {
        return mainDao.findEntity(Programmer.class, id);
    }

    @Override
    public void delete(Programmer programmer) {
        mainDao.remove(programmer);
    }

    @Override
    public Programmer findByEmail(String email) {
        return (Programmer) getSession().createCriteria(Programmer.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }
}
