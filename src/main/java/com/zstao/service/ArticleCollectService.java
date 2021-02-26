package com.zstao.service;

import com.zstao.entity.ArticleCollect;

import java.util.List;

/**
 * 文章收藏
 */
public interface ArticleCollectService {
    List<ArticleCollect> listSArticleCollects(Integer status);

//    ArticleCollect getSArticleCollect(Integer collectId);

    boolean saveSArticleCollect(ArticleCollect articleCollect);

    boolean deleteSArticleCollect(Integer collectId);

    boolean updateSArticleCollect(Integer status, Integer collectId);

    int articleCollectSNumber();

    List<ArticleCollect> listSArticleBeCollects(Integer articleId);

    // 用户收藏了哪些文章
    List<ArticleCollect> listSUserCollectArticles(Integer uid, Integer status);

}
