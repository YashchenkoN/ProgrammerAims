package com.programmer.media.dao;

import com.programmer.MainDao;
import com.programmer.media.FileEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kolyan on 8/27/15.
 */
@Repository
public class FileEntityDaoImpl implements FileEntityDao {

    @Autowired
    private MainDao mainDao;

    @Override
    public Session getSession() {
        return mainDao.getSession();
    }

    @Override
    public FileEntity get(Long id) {
        return mainDao.findEntity(FileEntity.class, id);
    }

    @Override
    public void add(FileEntity fileEntity) {
        mainDao.persist(fileEntity);
    }

    @Override
    public FileEntity update(FileEntity fileEntity) {
        return mainDao.merge(fileEntity);
    }

    @Override
    public void delete(FileEntity fileEntity) {
        mainDao.remove(fileEntity);
    }

}
