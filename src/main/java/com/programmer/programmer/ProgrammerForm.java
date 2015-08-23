package com.programmer.programmer;

import org.hibernate.validator.constraints.Email;

/**
 * Created by kolyan on 8/7/15.
 */
public class ProgrammerForm {

    @Email
    private String email;

    private String password;

    private String name;

    public ProgrammerForm() {

    }

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
