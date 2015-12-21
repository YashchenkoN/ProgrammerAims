package com.programmer.services.programmer;

import com.programmer.commons.ProgrammerRequest;
import com.programmer.entity.Programmer;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
public interface ProgrammerService {

    Programmer findByEmail(String email);

    Programmer findByActivationKey(String activationKey);

    Programmer findById(Long id);

    Programmer create(Programmer programmer);

    Programmer update(Programmer programmer);

    List<Programmer> getListOfProgrammers();

    Programmer getLoggedProgrammer();

    Programmer buildProgrammer(ProgrammerRequest authRequest);

    Programmer getByEmailAndPassword(String email, String password);
}
