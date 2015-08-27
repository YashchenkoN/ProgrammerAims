package com.programmer.media;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolyan on 8/27/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FileUploadResponse {
    private List<String> filePathes;
    private Long programmerId;

    public FileUploadResponse() {
        filePathes = new ArrayList<>();
    }

    public List<String> getFilePathes() {
        return filePathes;
    }

    public void setFilePathes(List<String> filePathes) {
        this.filePathes = filePathes;
    }

    public Long getProgrammerId() {
        return programmerId;
    }

    public void setProgrammerId(Long programmerId) {
        this.programmerId = programmerId;
    }

    public boolean addFilePath(String path) {
        return filePathes.add(path);
    }
}
