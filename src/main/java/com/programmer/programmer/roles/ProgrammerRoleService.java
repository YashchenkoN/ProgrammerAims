package com.programmer.programmer.roles;

import com.programmer.MainDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kolyan on 10/11/15.
 */
@Service
public class ProgrammerRoleService {

    @Autowired
    private MainDao mainDao;

    @Transactional(readOnly = true)
    public ProgrammerRole read(Roles role) {
        return (ProgrammerRole) mainDao.getSession().createCriteria(ProgrammerRole.class)
                .add(Restrictions.eq("role", role))
                .uniqueResult();
    }
}
