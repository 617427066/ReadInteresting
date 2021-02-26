package com.zstao.mapper;

import com.zstao.entity.ArticleType;

import java.util.List;

public interface ArticleTypeDao {
    List<ArticleType> listArticleTypes();

    String getArticleType(Integer atypeId);  //根据id 查询 类型名

    int articleTypeNumber();  //查询数量

    int saveArticleType(ArticleType articleType);  //插入数据

    int deleteArticleType(Integer atypeId);   //删除类型

    int updateArticleType(ArticleType articleType);  // 修改类型
}
