package com.zstao.service;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseService<T> {
    int add(T t);
    int delete(int id);
    int update(T t);
    T findOne(int id);
    List<T> findAll();
}
