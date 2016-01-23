package com.programmer.services.aim;

import com.programmer.entity.Aim;
import com.programmer.entity.Programmer;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
public interface AimService {

    Aim read(Long id);

    void create(Aim aim);

    Aim update(Aim aim);

    List<Aim> getListOfProgrammerAims(Programmer programmer);

    void delete(Aim aim);

}
