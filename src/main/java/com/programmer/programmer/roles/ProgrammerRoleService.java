package com.programmer.programmer.roles;

import com.programmer.MainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kolyan on 10/11/15.
 */
@Service
public class ProgrammerRoleService {

    @Autowired
    private MainDao mainDao;

    public ProgrammerRole read(Roles role) {
        return mainDao.findEntity(ProgrammerRole.class, role);
    }
}
