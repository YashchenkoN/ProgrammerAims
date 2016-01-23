package com.programmer.rest;

import com.programmer.api.error.ApiError;
import com.programmer.api.error.ApiException;
import com.programmer.api.programmer.ProgrammerCreateResponse;
import com.programmer.api.programmer.ProgrammerForm;
import com.programmer.api.programmer.ProgrammerRequest;
import com.programmer.entity.Programmer;
import com.programmer.entity.Roles;
import com.programmer.services.programmer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kolyan on 10/12/15.
 */
@RestController
@RequestMapping(value = "/com/programmer/api/programmer")
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

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProgrammerCreateResponse create(@RequestBody ProgrammerRequest programmerForm) throws ApiException {
        ProgrammerCreateResponse programmerCreateResponse = new ProgrammerCreateResponse();
        boolean exists = programmerService.findByEmail(programmerForm.getEmail()) != null;
        if(programmerFormValidator.validate(programmerForm) && !exists) {
            Programmer programmer = programmerBuilder.build(programmerForm, null);
            programmer.setRole(programmerRoleService.read(Roles.ROLE_UNACTIVE));
            programmerCreateResponse.setId(programmer.getId());
        } else if(!exists) {
            throw new ApiException(ApiError.WRONG_FIELD, programmerCreateResponse);
        } else {
            throw new ApiException(ApiError.USER_ALREADY_EXISTS, programmerCreateResponse);
        }
        programmerCreateResponse.setCreated(true);
        return programmerCreateResponse;
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProgrammerForm read(@PathVariable("id") String id) throws ApiException {
        Long longId;
        try {
            longId = Long.parseLong(id);
        } catch (Exception e) {
            longId = null;
        }
        ProgrammerForm programmer = programmerFormBuilder.build(longId);
        if(programmer == null) {
            throw new ApiException(ApiError.PROGRAMMER_NOT_FOUND, new ProgrammerForm());
        }
        return programmer;
    }
}
