package com.programmer.error;

import com.programmer.api.error.ApiError;
import com.programmer.api.error.ApiException;
import com.programmer.api.BaseApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Created by kolyan on 10/12/15.
 */
@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    @ResponseBody
    public BaseApiResponse handle(Exception e) {
        ApiException apiException = (ApiException) e;
        BaseApiResponse baseApiResponse = ((ApiException) e).getBaseApiResponse();

        if(baseApiResponse == null) {
            baseApiResponse = new BaseApiResponse();
        }
        int position = Arrays.binarySearch(ApiError.values(), apiException.getErrorValue());
        baseApiResponse.setErrorValue(apiException.getErrorValue().toString());
        baseApiResponse.setErrorCode(position);
        return baseApiResponse;
    }
}
