package com.programmer.aim;

import com.programmer.programmer.Programmer;
import com.programmer.step.Step;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */

@Entity
@Table(name = "aim")
public class Aim {

    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "description")
    @NotNull
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aim")
    private List<Step> steps;

    @Column(name = "priority")
    @NotNull
    private Long priority;

    @Column(name = "added")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar added;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "programmer_id")
    private Programmer programmer;

    public Aim() {
        steps = new ArrayList<>();
        added = Calendar.getInstance();
    }

    public Aim(String name, String description, Long priority) {
        this();
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    public Aim(String name, String description, List<Step> steps, Long priority) {
        added = Calendar.getInstance();
        this.name = name;
        this.description = description;
        this.steps = steps;
        this.priority = priority;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Calendar getAdded() {
        return added;
    }

    public void setAdded(Calendar added) {
        this.added = added;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    public void addStep(Step step) {
        if(step.getAim() != this)
            step.setAim(this);
        steps.add(step);
    }

    public void remove(Step step) {
        steps.remove(step);
    }

    @Override
    public String toString() {
        return "[name=" + name + ", description=" + description;
    }
}