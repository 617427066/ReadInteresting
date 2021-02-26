package com.zstao.service;


import com.zstao.entity.ArticleType;

import java.util.List;


public interface ArticleTypeService {
    List<ArticleType> listArticleTypes();

    String getArticleType(Integer atypeId);  //根据id 查询 类型名

    int articleTypeNumber();  //查询数量

    boolean saveArticleType(ArticleType articleType);  //插入数据

    boolean deleteArticleType(Integer atypeId);   //删除类型

    boolean updateArticleType(ArticleType articleType);  // 修改类型
}
