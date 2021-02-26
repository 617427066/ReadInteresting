package com.zstao.service;

import com.zstao.entity.School;

import java.util.List;

public interface SchoolService extends BaseService<School>{
    List<School> findAll();

    School getSch(Integer id);

    boolean updateShc(School school);

    boolean saveSch(School school);

    boolean deleteSch(Integer id);
}
