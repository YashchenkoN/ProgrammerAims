package com.programmer.rest.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by kolyan on 10/12/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseApiResponse {
    public Integer error;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }
}
