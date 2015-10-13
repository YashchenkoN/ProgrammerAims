package com.programmer.programmer;

import org.hibernate.validator.constraints.Email;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by kolyan on 8/7/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProgrammerSignupForm {

    @Email
    private String email;
    private String password;
    private String name;

    public ProgrammerSignupForm() {}

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
