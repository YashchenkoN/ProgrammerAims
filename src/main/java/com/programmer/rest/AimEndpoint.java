package com.programmer.rest;

import com.programmer.rest.beans.AimCreateRequest;
import com.programmer.rest.beans.AimCreateResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kolyan on 10/14/15.
 */
@RestController
@RequestMapping(value = "/api/aim")
public class AimEndpoint {

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AimCreateResponse create(@RequestBody AimCreateRequest aimCreateRequest) {
        AimCreateResponse aimCreateResponse = new AimCreateResponse();
        return aimCreateResponse;
    }
}
