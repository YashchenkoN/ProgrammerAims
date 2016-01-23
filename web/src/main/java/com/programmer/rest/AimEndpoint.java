package com.programmer.rest;

import com.programmer.api.aim.AimCreateRequest;
import com.programmer.api.aim.AimCreateResponse;
import com.programmer.api.aim.AimDeleteRequest;
import com.programmer.api.aim.AimDeleteResponse;
import com.programmer.entity.Aim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.programmer.services.aim.AimService;

/**
 * Created by kolyan on 10/14/15.
 */
@RestController
@RequestMapping(value = "/com/programmer/api/aim")
public class AimEndpoint {

    @Autowired
    private AimService aimService;

    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AimCreateResponse create(@RequestBody AimCreateRequest aimCreateRequest) {
        AimCreateResponse aimCreateResponse = new AimCreateResponse();
        // todo creating aim
        return aimCreateResponse;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AimDeleteResponse delete(@RequestBody AimDeleteRequest deleteRequest) {
        AimDeleteResponse aimDeleteResponse = new AimDeleteResponse();
        Aim aim = aimService.read(deleteRequest.getId());
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
