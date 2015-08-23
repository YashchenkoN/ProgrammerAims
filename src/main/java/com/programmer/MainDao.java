package com.programmer;

import org.hibernate.Session;

import java.io.Serializable;

/**
 * Created by kolyan on 8/11/15.
 */
public interface MainDao {

    public Session getSession();

    public <E> E merge(E entity);

    public <E> void persist(E entity);

    public <E> E findEntity(Class<E> entity, Object id);

    public <E> void remove(E entity);

}
