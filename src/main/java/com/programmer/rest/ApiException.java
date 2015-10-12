package com.programmer.rest;

import com.programmer.rest.beans.BaseApiResponse;

/**
 * Created by kolyan on 10/12/15.
 */
public class ApiException extends Exception {
    private Integer error;
    private BaseApiResponse baseApiResponse;

    public ApiException() {}

    public ApiException(Integer error, BaseApiResponse baseApiResponse) {
        this.error = error;
        this.baseApiResponse = baseApiResponse;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public BaseApiResponse getBaseApiResponse() {
        return baseApiResponse;
    }

    public void setBaseApiResponse(BaseApiResponse baseApiResponse) {
        this.baseApiResponse = baseApiResponse;
    }
}
