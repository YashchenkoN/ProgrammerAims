package com.programmer.rest;

import com.programmer.programmer.*;
import com.programmer.programmer.roles.ProgrammerRoleService;
import com.programmer.programmer.roles.Roles;
import com.programmer.programmer.service.ProgrammerService;
import com.programmer.rest.beans.ProgrammerCreateResponse;
import com.programmer.rest.beans.ReadProgrammerRequest;
import com.programmer.services.ProgrammerFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kolyan on 10/12/15.
 */
@RestController
@RequestMapping(value = "/api/programmer")
public class ProgrammerEndpoint {

    @Autowired
    private ProgrammerFormValidator programmerFormValidator;

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private ProgrammerBuilder programmerBuilder;

    @Autowired
    private ProgrammerRoleService programmerRoleService;

    @Autowired
    private ProgrammerFormBuilder programmerFormBuilder;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProgrammerCreateResponse create(@RequestBody ProgrammerSignupForm programmerForm) throws ApiException {
        ProgrammerCreateResponse programmerCreateResponse = new ProgrammerCreateResponse();
        boolean exists = programmerService.findByEmail(programmerForm.getEmail()) != null;
        if(programmerFormValidator.validate(programmerForm) && !exists) {
            Programmer programmer = programmerBuilder.build(programmerForm, null);
            programmer.setRole(programmerRoleService.read(Roles.ROLE_UNACTIVE));
            programmerCreateResponse.setId(programmer.getId());
        } else if(!exists) {
            throw new ApiException(100, programmerCreateResponse);
        } else {
            throw new ApiException(100, programmerCreateResponse);
        }
        programmerCreateResponse.setCreated(true);
        return programmerCreateResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProgrammerForm read(@RequestParam("id") String id) throws ApiException {
        Long longId;
        try {
            longId = Long.parseLong(id);
        } catch (Exception e) {
            longId = null;
        }
        ProgrammerForm programmer = programmerFormBuilder.build(longId);
        if(programmer == null) {
            throw new ApiException(100, programmer);
        }
        return programmer;
    }
}
