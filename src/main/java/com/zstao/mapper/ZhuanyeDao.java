package com.zstao.mapper;

import com.zstao.entity.School;
import com.zstao.entity.Zhuanye;

import java.util.List;

public interface ZhuanyeDao {
    List<Zhuanye> findAll();

    Zhuanye findOne(Integer id);

    int delete(Integer id);

    int update(Zhuanye zy);

    int save(Zhuanye zy);

    List<Zhuanye> findBySch(String sch_id);
}
