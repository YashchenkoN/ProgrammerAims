package com.programmer.rest;

import com.programmer.commons.AuthResponse;
import com.programmer.commons.ProgrammerRequest;
import com.programmer.entity.Programmer;
import com.programmer.services.programmer.ProgrammerFormValidator;
import com.programmer.services.programmer.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kolyan on 21.12.15.
 */
@RestController
@RequestMapping(value = "/api/user/")
public class UserEndpoint {

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProgrammerFormValidator validator;

    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ACTIVE, ROLE_UNACTIVE, ROLE_ADMIN')")
    @RequestMapping(value = "settings", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponse saveSettings(@RequestBody ProgrammerRequest authRequest) {
        AuthResponse authResponse = new AuthResponse();
        Programmer programmer = programmerService.getLoggedProgrammer();
        if(programmer != null) {
            boolean isValidEmail = validator.validateEmail(authRequest.getEmail());
            if(!programmer.getEmail().equals(authRequest.getEmail())
                    && isValidEmail) {
                programmer.setEmail(authRequest.getEmail());
            }
            if(validator.validateName(authRequest.getName())) {
                programmer.setName(authRequest.getName());
            }
            if(authRequest.getPassword() != null && authRequest.getPassword().length() > 5) {
                programmer.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            }
            programmerService.update(programmer);
        }
        return authResponse;
    }
}
