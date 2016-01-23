package com.programmer.entity;

import com.programmer.utils.KeyGenerationUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

/**
 * Created by kolyan on 8/4/15.
 */
@Entity
@Table(name = "programmers")
public class Programmer implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true)
    @NotNull
    private String email;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private Calendar registrationDate;

    @Column(name = "last_visit")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastVisit;

    @Column(name = "activation_key")
    private String activationKey;

    @JoinColumn(name = "role")
    @ManyToOne(fetch = FetchType.EAGER)
    private ProgrammerRole role;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Blog> blogs;

    public Programmer() {
        registrationDate = Calendar.getInstance();
        lastVisit = Calendar.getInstance();
        activationKey = KeyGenerationUtil.getKey();
    }

    public Programmer(Programmer user) {
        this();
        if(user != null) {
            this.email = user.email;
            this.name = user.name;
            this.role = user.role;
            this.activationKey = user.activationKey;
        }
    }

    public Programmer(String email, String name, String password, ProgrammerRole role) {
        this();
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Calendar registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Calendar getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Calendar lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public ProgrammerRole getRole() {
        return role;
    }

    public void setRole(ProgrammerRole role) {
        this.role = role;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

}
