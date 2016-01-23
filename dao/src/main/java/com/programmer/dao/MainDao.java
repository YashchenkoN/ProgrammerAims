package com.programmer.dao;

import org.hibernate.Session;

/**
 * Created by kolyan on 8/11/15.
 */
public interface MainDao {

    Session getSession();

    <E> E merge(E entity);

    <E> void persist(E entity);

    <E> E findEntity(Class<E> entity, Object id);

    <E> void remove(E entity);

}
