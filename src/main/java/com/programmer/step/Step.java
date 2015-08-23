package com.programmer.step;

import com.programmer.aim.Aim;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by kolyan on 8/11/15.
 */
@Entity
@Table(name = "step")
public class Step {

    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @Column(name = "difficult")
    @NotNull
    private Difficult difficult;

    @Column(name = "specification")
    @NotNull
    private String specification;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinTable(name = "aim", joinColumns = @JoinColumn(name = "step_id"), inverseJoinColumns = @JoinColumn(name = "aim_id"))
    private Aim aim;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Difficult getDifficult() {
        return difficult;
    }

    public void setDifficult(Difficult difficult) {
        this.difficult = difficult;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Aim getAim() {
        return aim;
    }

    public void setAim(Aim aim) {
        this.aim = aim;
    }
}
