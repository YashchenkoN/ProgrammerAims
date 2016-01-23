package com.programmer.api.error;

import com.programmer.api.BaseApiResponse;

/**
 * Created by kolyan on 10/12/15.
 */
public class ApiException extends Exception {
    private Integer errorCode;
    private ApiError errorValue;
    private BaseApiResponse baseApiResponse;

    public ApiException() {}

    public ApiException(ApiError errorValue, BaseApiResponse baseApiResponse) {
        this.errorValue = errorValue;
        this.baseApiResponse = baseApiResponse;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public ApiError getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(ApiError errorValue) {
        this.errorValue = errorValue;
    }

    public BaseApiResponse getBaseApiResponse() {
        return baseApiResponse;
    }

    public void setBaseApiResponse(BaseApiResponse baseApiResponse) {
        this.baseApiResponse = baseApiResponse;
    }
}
