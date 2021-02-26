package com.zstao.mapper;

import com.zstao.entity.ArticleCollect;

import java.util.List;

/**
 * 文章收藏dao层
 */
public interface ArticleCollectDao {

    List<ArticleCollect> listArticleCollects(Integer status);


    int saveArticleCollect(ArticleCollect articleCollect);

    int deleteArticleCollect(Integer collectId); // 删除，管理员删除

    int updateArticleCollect(Integer status, Integer collectId);  //取消收藏

    int ArticleCollectNumber();  //数据总数

    //文章被收藏
    List<ArticleCollect> listArticleBeCollects(Integer articleId);

    // 用户收藏了哪些文章
    List<ArticleCollect> listUserCollectArticles(Integer uid, Integer status);


}
