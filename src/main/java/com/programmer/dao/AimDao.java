package com.programmer.dao;

import com.programmer.entity.Aim;
import org.hibernate.Session;

/**
 * Created by kolyan on 8/11/15.
 */
public interface AimDao {

    public Session getSession();

    public void add(Aim aim);

    public Aim update(Aim aim);

    public Aim get(Long id);

    public void delete(Aim aim);

}
