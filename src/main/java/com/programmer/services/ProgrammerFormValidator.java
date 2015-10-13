package com.programmer.services;

import com.programmer.programmer.ProgrammerSignupForm;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;

/**
 * Created by kolyan on 10/13/15.
 */
@Service
public class ProgrammerFormValidator {

    public boolean validate(ProgrammerSignupForm programmerForm) {
        return validateEmail(programmerForm.getEmail())
                && validateName(programmerForm.getName());
    }

    private boolean validateName(String name) {
        return name != null && !name.isEmpty();
    }

    private boolean validateEmail(String email) {
        if(email != null) {
            try {
                InternetAddress e = new InternetAddress(email);
                e.validate();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    private boolean validatePassword(String password) {
        return password != null && !password.isEmpty();
    }
}
