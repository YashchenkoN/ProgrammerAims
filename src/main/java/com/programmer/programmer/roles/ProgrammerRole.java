package com.programmer.programmer.roles;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by kolyan on 10/10/15.
 */
@Entity
@Table(name = "programmer_role")
public class ProgrammerRole implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name", unique = true)
    @Enumerated(value = EnumType.STRING)
    private Roles role;

    public ProgrammerRole() {
        this.role = Roles.ROLE_UNACTIVE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof ProgrammerRole)) return false;
        ProgrammerRole pRole = (ProgrammerRole) o;
        return role == pRole.role;
    }

    @Override
    public String toString() {
        return role.name();
    }

}
