package com.programmer.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by kolyan on 8/11/15.
 */

@Repository
public class MainDaoImpl implements MainDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Session getSession() {
        if (entityManager != null && entityManager.getDelegate() != null) {
            return ((Session) entityManager.getDelegate());
        }
        return null;
    }

    @Override
    public <E> E merge(E entity) {
        return (E) getSession().merge(entity);
    }

    @Override
    public <E> void persist(E entity) {
        getSession().persist(entity);
    }

    @Override
    public <E> E findEntity(Class<E> entity, Object id) {
        if (id == null) {
            return null;
        }
        return (E) getSession().get(entity, (Serializable) id);
    }

    @Override
    public <E> void remove(E entity) {
        if (entity != null) {
            if (!entityManager.contains(entity)) {
                entity = entityManager.merge(entity);
            }
            entityManager.remove(entity);
        }
    }
}
