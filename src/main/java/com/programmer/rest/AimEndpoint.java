package com.programmer.rest;

import com.programmer.commons.AimDeleteRequest;
import com.programmer.commons.AimDeleteResponse;
import com.programmer.entity.Aim;
import com.programmer.rest.beans.AimCreateRequest;
import com.programmer.rest.beans.AimCreateResponse;
import com.programmer.services.aim.AimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kolyan on 10/14/15.
 */
@RestController
@RequestMapping(value = "/api/aim")
public class AimEndpoint {

    @Autowired
    private AimService aimService;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AimCreateResponse create(@RequestBody AimCreateRequest aimCreateRequest) {
        AimCreateResponse aimCreateResponse = new AimCreateResponse();
        return aimCreateResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AimDeleteResponse delete(@RequestBody AimDeleteRequest deleteRequest) {
        AimDeleteResponse aimDeleteResponse = new AimDeleteResponse();
        Aim aim = aimService.findById(deleteRequest.getId());
        if(aim != null) {
            aimService.delete(aim);
            aimDeleteResponse.setIsDeleted(true);
            aimDeleteResponse.setDeletedId(deleteRequest.getId());
        } else {
            aimDeleteResponse.setIsDeleted(false);
        }
        return aimDeleteResponse;
    }
}
