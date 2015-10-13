package com.programmer.programmer;

import com.programmer.aim.AimForm;
import com.programmer.blog.BlogForm;
import com.programmer.rest.beans.BaseApiResponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kolyan on 10/13/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProgrammerForm extends BaseApiResponse {

    private Long id;
    private String email;
    private String name;
    private String registrationDate;
    private String lastVisitDate;
    private List<AimForm> aims;
    private List<BlogForm> blogs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(String lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public List<AimForm> getAims() {
        return aims;
    }

    public void setAims(List<AimForm> aims) {
        this.aims = aims;
    }

    public List<BlogForm> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogForm> blogs) {
        this.blogs = blogs;
    }
}
