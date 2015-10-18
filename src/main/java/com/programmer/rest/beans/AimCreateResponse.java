package com.programmer.rest.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by kolyan on 10/14/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AimCreateResponse extends BaseApiResponse {
    private Long id;
    private boolean created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }
}
