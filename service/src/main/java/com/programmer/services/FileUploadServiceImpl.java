package com.programmer.services;

import com.programmer.dao.FileEntityDao;
import com.programmer.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kolyan on 8/27/15.
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileEntityDao fileEntityDao;

    @Transactional(readOnly = true)
    @Override
    public FileEntity read(Long id) {
        return fileEntityDao.get(id);
    }

    @Transactional
    @Override
    public void create(FileEntity fileEntity) {
        fileEntityDao.add(fileEntity);
    }

    @Transactional
    @Override
    public FileEntity update(FileEntity fileEntity) {
        return fileEntityDao.update(fileEntity);
    }
}
