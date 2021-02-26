package com.zstao.mapper;

import com.zstao.entity.School;
import com.zstao.entity.Zhuanye;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SchoolDao {
    List<School> findAll();

    List<School> listSchool(String status);

    School findOne(Integer id);

    int delete(Integer id);

    int update(School school);

    int save(School school);

    List<Zhuanye> findByZy(String zy_id);


}
