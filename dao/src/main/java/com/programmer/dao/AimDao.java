package com.programmer.dao;

import com.programmer.entity.Aim;
import com.programmer.entity.Programmer;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
public interface AimDao {

    void create(Aim aim);

    Aim update(Aim aim);

    Aim read(Long id);

    void delete(Aim aim);

    Aim getByName(String name);

    List<Aim> getAimsByProgrammer(Programmer programmer);
}
