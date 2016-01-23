package com.programmer.services;

import com.programmer.entity.FileEntity;

/**
 * Created by kolyan on 8/27/15.
 */
public interface FileUploadService {

    FileEntity read(Long id);

    void create(FileEntity fileEntity);

    FileEntity update(FileEntity fileEntity);
}
