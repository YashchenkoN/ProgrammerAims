package com.programmer.programmer;

import com.programmer.config.WebAppConfigurationAware;
import com.programmer.programmer.roles.ProgrammerRole;
import com.programmer.programmer.roles.ProgrammerRoleService;
import com.programmer.programmer.roles.Roles;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by kolyan on 10/18/15.
 */
public class ProgrammerRolesInDbTest extends WebAppConfigurationAware {

    @Autowired
    private ProgrammerRoleService programmerRoleService;

    @Test
    public void test() throws Exception {
        ProgrammerRole programmerRoleUnactive = programmerRoleService.read(Roles.ROLE_UNACTIVE),
                programmerRoleActive = programmerRoleService.read(Roles.ROLE_ACTIVE),
                programmerRoleAdmin = programmerRoleService.read(Roles.ROLE_ADMIN);
        Assert.assertNotNull(programmerRoleUnactive);
        Assert.assertNotNull(programmerRoleActive);
        Assert.assertNotNull(programmerRoleAdmin);
    }
}
