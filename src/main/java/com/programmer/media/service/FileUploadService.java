package com.programmer.media.service;

import com.programmer.media.FileEntity;
import com.programmer.entity.Programmer;

import java.util.List;

/**
 * Created by kolyan on 8/27/15.
 */
public interface FileUploadService {

    FileEntity findById(Long id);

    void add(FileEntity fileEntity);

    FileEntity update(FileEntity fileEntity);

    List<FileEntity> getListOfProgrammerFiles(Programmer programmer);

}
