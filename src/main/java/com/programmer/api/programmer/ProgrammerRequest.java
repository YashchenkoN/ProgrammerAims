package com.programmer.api.programmer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by kolyan on 8/7/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProgrammerRequest {
    private String email;
    private String password;
    private String name;

    public ProgrammerRequest() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
