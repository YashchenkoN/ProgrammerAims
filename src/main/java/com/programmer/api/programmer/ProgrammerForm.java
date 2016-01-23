package com.programmer.api.programmer;

import com.programmer.api.BaseApiResponse;
import com.programmer.api.blog.BlogForm;
import com.programmer.api.aim.AimForm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by kolyan on 10/13/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProgrammerForm extends BaseApiResponse {

    private Long id;
    private String email;
    private String role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
