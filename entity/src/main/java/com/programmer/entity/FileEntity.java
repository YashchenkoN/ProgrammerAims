package com.programmer.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Created by kolyan on 8/27/15.
 */
@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @Column(name = "file_path")
    @NotNull
    private String filePath;

    @OneToOne
    @JoinColumn(name = "owner")
    private Programmer programmer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

}
