package com.programmer.aim.service;

import com.programmer.aim.Aim;
import com.programmer.programmer.Programmer;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
public interface AimService {

    public Aim findByName(String name);

    public Aim findById(Long id);

    public void add(Aim aim);

    public Aim update(Aim aim);

    public List<Aim> getListOfProgrammerAims(Programmer programmer);

    public void delete(Aim aim);

}
