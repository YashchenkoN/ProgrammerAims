package com.programmer.media.service;

import com.programmer.media.FileEntity;
import com.programmer.media.dao.FileEntityDao;
import com.programmer.programmer.Programmer;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kolyan on 8/27/15.
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileEntityDao fileEntityDao;

    @Transactional(readOnly = true)
    @Override
    public FileEntity findById(Long id) {
        return fileEntityDao.get(id);
    }

    @Transactional
    @Override
    public void add(FileEntity fileEntity) {
        fileEntityDao.add(fileEntity);
    }

    @Transactional
    @Override
    public FileEntity update(FileEntity fileEntity) {
        return fileEntityDao.update(fileEntity);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<FileEntity> getListOfProgrammerFiles(Programmer programmer) {
        return fileEntityDao.getSession()
                .createCriteria(FileEntity.class)
                .add(Restrictions.eq("programmer", programmer))
                .list();
    }
}
