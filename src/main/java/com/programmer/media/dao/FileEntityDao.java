package com.programmer.media.dao;

import com.programmer.media.FileEntity;

import javax.mail.Session;
import java.io.File;

/**
 * Created by kolyan on 8/27/15.
 */
public interface FileEntityDao {

    org.hibernate.Session getSession();

    FileEntity get(Long id);

    void add(FileEntity fileEntity);

    FileEntity update(FileEntity fileEntity);

    void delete(FileEntity fileEntity);

}
