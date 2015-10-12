package com.programmer.rest.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by kolyan on 10/12/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProgrammerCreateResponse extends BaseApiResponse {
    private Long id;
    private Boolean created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCreated() {
        return created;
    }

    public void setCreated(Boolean created) {
        this.created = created;
    }
}
