package com.programmer.programmer;

import com.programmer.aim.Aim;
import com.programmer.blog.Blog;
import com.programmer.media.FileEntity;
import com.programmer.programmer.roles.ProgrammerRole;
import com.programmer.utils.KeyGenerationUtil;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

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
    @NotNull
    private Long id;

    @Column(name = "email", unique = true)
    @NotEmpty
    private String email;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private Calendar registrationDate;

    @Column(name = "last_visit")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastVisit;

    @Column(name = "activation_key")
    private String activationKey;

    @Column(name = "role")
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private ProgrammerRole role;

    @OneToOne
    @JoinColumn(name = "avatar_id")
    private FileEntity fileEntity;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Aim> aims;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Blog> blogs;

    public Programmer() {
        registrationDate = Calendar.getInstance();
        lastVisit = Calendar.getInstance();
        activationKey = KeyGenerationUtil.getKey();
        aims = new ArrayList<>();
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

    public FileEntity getFileEntity() {
        return fileEntity;
    }

    public void setFileEntity(FileEntity fileEntity) {
        this.fileEntity = fileEntity;
    }

    public List<Aim> getAims() {
        return aims;
    }

    public void setAims(List<Aim> aims) {
        this.aims = aims;
    }

    public void addAim(Aim aim) {
        if(aim.getProgrammer() != this)
            aim.setProgrammer(this);
        aims.add(aim);
    }

    public void removeAim(Aim aim) {
        aims.remove(aim);
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

}
