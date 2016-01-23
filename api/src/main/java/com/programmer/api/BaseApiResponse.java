package com.programmer.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by kolyan on 10/12/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseApiResponse {
    private Integer errorCode;
    private String errorValue;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(String errorValue) {
        this.errorValue = errorValue;
    }
}
