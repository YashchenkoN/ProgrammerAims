package com.programmer.programmer;

import com.programmer.config.WebTestConfig;
import com.programmer.services.ProgrammerFormValidator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by kolyan on 10/16/15.
 */
public class ProgrammerFormValidatorTest extends WebTestConfig {

    @Autowired
    private ProgrammerFormValidator programmerFormValidator;

    @Test
    public void test() {
        ProgrammerSignupForm programmerSignupForm = new ProgrammerSignupForm();
        programmerSignupForm.setEmail("email@email.com");
        programmerSignupForm.setName("Name Name");
        Assert.assertEquals(true, programmerFormValidator.validate(programmerSignupForm));
    }
}
