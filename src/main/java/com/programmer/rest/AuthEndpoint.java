package com.programmer.rest;

import com.programmer.api.auth.AuthResponse;
import com.programmer.services.MailService;
import com.programmer.entity.Programmer;
import com.programmer.api.programmer.ProgrammerRequest;
import com.programmer.services.programmer.ProgrammerRoleService;
import com.programmer.entity.Roles;
import com.programmer.services.programmer.ProgrammerFormValidator;
import com.programmer.services.programmer.ProgrammerDetailsService;
import com.programmer.services.programmer.ProgrammerService;
import com.programmer.utils.KeyGenerationUtil;
import com.programmer.utils.LinkGenerationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;


/**
 * Created by kolyan on 20.12.15.
 */
@RestController
@RequestMapping(value = "/api/")
public class AuthEndpoint {

    @Autowired
    private ProgrammerFormValidator validator;

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private ProgrammerRoleService programmerRoleService;

    @Autowired
    private ProgrammerDetailsService programmerDetailsService;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "register", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponse reg(@RequestBody ProgrammerRequest signupForm, HttpServletRequest request) {
        AuthResponse authResponse = new AuthResponse();
        boolean isNameValid = validator.validateName(signupForm.getName());
        boolean isEmailValid = validator.validateEmail(signupForm.getEmail());
        boolean isUserNotExists = programmerService.findByEmail(signupForm.getEmail()) == null;
        if(isEmailValid && isNameValid && isUserNotExists) {
            Programmer programmer = programmerService.buildProgrammer(signupForm);
            String key = KeyGenerationUtil.getKey();
            programmer.setPassword(key);
            programmer.setRegistrationDate(Calendar.getInstance());
            programmer.setRole(programmerRoleService.read(Roles.ROLE_UNACTIVE));
            programmerService.create(programmer);
            programmerDetailsService.signin(programmer);
            String activationLink = LinkGenerationUtil.getActivationLink(request, programmer.getActivationKey());
            mailService.sendMail(programmer.getEmail(), "Password", key);
            mailService.sendMail(programmer.getEmail(), "Activation", activationLink);
            authResponse.setRedirectUrl("/");
            authResponse.setResult(true);
        } else if(isEmailValid && isUserNotExists) {
            authResponse.setResult(false);
            authResponse.setMessage("Name not valid");
        } else if(isNameValid && isUserNotExists) {
            authResponse.setResult(false);
            authResponse.setMessage("Email not valid");
        } else if(isUserNotExists) {
            authResponse.setResult(false);
            authResponse.setMessage("Email and name not valid");
        } else {
            authResponse.setResult(false);
            authResponse.setMessage("User already exists");
        }
        return authResponse;
    }

    @RequestMapping(value = "auth", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponse auth(@RequestBody ProgrammerRequest authRequest) {
        AuthResponse authResponse = new AuthResponse();
        Programmer programmer = programmerService.getByEmailAndPassword(authRequest.getEmail(), authRequest.getPassword());
        if(programmer != null) {
            programmerDetailsService.signin(programmer);
            authResponse.setResult(true);
            authResponse.setRedirectUrl("/");
        } else {
            authResponse.setResult(false);
            authResponse.setMessage("Email or password incorrect");
        }
        return authResponse;
    }
}
