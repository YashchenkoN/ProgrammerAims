package com.programmer.dao;


import com.programmer.entity.FileEntity;

/**
 * Created by kolyan on 8/27/15.
 */
public interface FileEntityDao {

    FileEntity get(Long id);

    void add(FileEntity fileEntity);

    FileEntity update(FileEntity fileEntity);

    void delete(FileEntity fileEntity);

}
