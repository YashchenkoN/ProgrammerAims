package com.programmer.api.aim;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by kolyan on 10/14/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AimCreateRequest implements Serializable {
    private Long programmerId;
    private AimForm aimForm;

    public Long getProgrammerId() {
        return programmerId;
    }

    public void setProgrammerId(Long programmerId) {
        this.programmerId = programmerId;
    }

    public AimForm getAimForm() {
        return aimForm;
    }

    public void setAimForm(AimForm aimForm) {
        this.aimForm = aimForm;
    }
}
