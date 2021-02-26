package com.zstao.service.serviceImpl;

import com.zstao.entity.School;
import com.zstao.mapper.SchoolDao;
import com.zstao.service.SchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Resource
    private SchoolDao schoolDao;
    @Override
    public List<School> findAll() {
        return schoolDao.findAll();
    }

    @Override
    public School getSch(Integer id) {
        return schoolDao.findOne(id);
    }

    @Override
    public boolean updateShc(School school) {
        if(schoolDao.update(school)!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean saveSch(School school) {
        if(schoolDao.save(school)!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteSch(Integer id) {
        if(schoolDao.delete(id)!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int add(School school) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int update(School school) {
        return 0;
    }

    @Override
    public School findOne(int id) {
        return null;
    }


}
