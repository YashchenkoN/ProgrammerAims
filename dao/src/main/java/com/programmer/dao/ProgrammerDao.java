package com.programmer.dao;


import com.programmer.entity.Programmer;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
public interface ProgrammerDao {

    void create(Programmer programmer);

    Programmer update(Programmer programmer);

    Programmer read(Long id);

    void delete(Programmer programmer);

    Programmer findByEmail(String email);

    Programmer findByActivationKey(String activationKey);

    List<Programmer> getListOfProgrammers();
}
