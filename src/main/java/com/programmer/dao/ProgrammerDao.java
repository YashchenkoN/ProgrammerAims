package com.programmer.dao;

import com.programmer.entity.Programmer;
import org.hibernate.Session;

/**
 * Created by kolyan on 8/11/15.
 */
public interface ProgrammerDao {

    public Session getSession();

    public void add(Programmer programmer);

    public Programmer update(Programmer programmer);

    public Programmer get(Long id);

    public void delete(Programmer programmer);

    public Programmer findByEmail(String email);

}
