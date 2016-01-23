package com.programmer.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by kolyan on 20.12.15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseResponse {
    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
