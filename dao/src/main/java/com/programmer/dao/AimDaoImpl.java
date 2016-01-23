package com.programmer.dao;

import com.programmer.entity.Aim;
import com.programmer.entity.Programmer;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
@Repository
public class AimDaoImpl implements AimDao {

    @Autowired
    private MainDao mainDao;

    @Override
    public void create(Aim aim) {
        mainDao.persist(aim);
    }

    @Override
    public Aim update(Aim aim) {
        return mainDao.merge(aim);
    }

    @Override
    public Aim read(Long id) {
        return mainDao.findEntity(Aim.class, id);
    }

    @Override
    public void delete(Aim aim) {
        mainDao.remove(aim);
    }

    @Override
    public Aim getByName(String name) {
        return (Aim) mainDao.getSession()
                .createCriteria(Aim.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Aim> getAimsByProgrammer(Programmer programmer) {
        return mainDao.getSession()
                .createCriteria(Aim.class)
                .add(Restrictions.eq("programmer", programmer))
                .list();
    }
}
