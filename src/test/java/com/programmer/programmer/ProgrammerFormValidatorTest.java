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
    public void testValidData() throws Exception {
        ProgrammerSignupForm programmerSignupForm = new ProgrammerSignupForm();
        programmerSignupForm.setEmail("email@email.com");
        programmerSignupForm.setName("Name Name");
        Assert.assertTrue("It passed test", programmerFormValidator.validate(programmerSignupForm));
    }

    @Test
    public void testNotValidEmail() throws Exception {
        ProgrammerSignupForm programmerSignupForm = new ProgrammerSignupForm();
        programmerSignupForm.setEmail("email");
        programmerSignupForm.setName("Name Name");
        Assert.assertFalse("Not valid email", programmerFormValidator.validate(programmerSignupForm));
    }

    @Test
    public void testNotValidName() throws Exception {
        ProgrammerSignupForm programmerSignupForm = new ProgrammerSignupForm();
        programmerSignupForm.setEmail("email@email.com");
        programmerSignupForm.setName(null);
        Assert.assertFalse("Not valid name", programmerFormValidator.validate(programmerSignupForm));
    }

}
