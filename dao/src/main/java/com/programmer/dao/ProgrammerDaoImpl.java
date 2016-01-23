package com.programmer.dao;

import com.programmer.entity.Programmer;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */

@Repository
public class ProgrammerDaoImpl implements ProgrammerDao {

    @Autowired
    private MainDao mainDao;

    @Override
    public void create(Programmer programmer) {
        mainDao.persist(programmer);
    }

    @Override
    public Programmer update(Programmer programmer) {
        return mainDao.merge(programmer);
    }

    @Override
    public Programmer read(Long id) {
        return mainDao.findEntity(Programmer.class, id);
    }

    @Override
    public void delete(Programmer programmer) {
        mainDao.remove(programmer);
    }

    @Override
    public Programmer findByEmail(String email) {
        return (Programmer) mainDao.getSession().createCriteria(Programmer.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public Programmer findByActivationKey(String activationKey) {
        return (Programmer) mainDao.getSession()
                .createCriteria(Programmer.class)
                .add(Restrictions.eq("activationKey", activationKey))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Programmer> getListOfProgrammers() {
        return mainDao.getSession()
                .createCriteria(Programmer.class)
                .list();
    }
}
