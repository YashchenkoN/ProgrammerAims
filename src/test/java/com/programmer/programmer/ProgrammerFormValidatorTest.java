package com.programmer.programmer;

import com.programmer.config.WebAppConfigurationAware;
import com.programmer.services.ProgrammerFormValidator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by kolyan on 10/16/15.
 */
public class ProgrammerFormValidatorTest extends WebAppConfigurationAware {

    @Autowired
    private ProgrammerFormValidator programmerFormValidator;

    @Test
    public void test() throws Exception {
        ProgrammerSignupForm programmerSignupForm = new ProgrammerSignupForm();
        programmerSignupForm.setEmail("email@email.com");
        programmerSignupForm.setName("Name Name");
        Assert.assertTrue("It passed test", programmerFormValidator.validate(programmerSignupForm));
    }
}
