package com.programmer.services.aim;

import com.programmer.dao.AimDao;
import com.programmer.entity.Aim;
import com.programmer.entity.Programmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
@Service
public class AimServiceImpl implements AimService {

    @Autowired
    private AimDao aimDao;

    @Transactional(readOnly = true)
    @Override
    public Aim read(Long id) {
        return aimDao.read(id);
    }

    @Transactional
    @Override
    public void create(Aim aim) {
        aimDao.create(aim);
    }

    @Transactional
    @Override
    public Aim update(Aim aim) {
        return aimDao.update(aim);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Aim> getListOfProgrammerAims(Programmer programmer) {
        return aimDao.getAimsByProgrammer(programmer);
    }

    @Transactional
    @Override
    public void delete(Aim aim) {
        aimDao.delete(aim);
    }
}
