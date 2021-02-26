package com.zstao.service;


import com.zstao.entity.Article;

import java.util.List;

/**
 * 文章
 */
public interface ArticleService {
    List<Article> listSArticles(Integer status,Integer pageSize,Integer pageNum);

    Article getSArticle(Integer aid);

    boolean saveSArticle(Article article);

    boolean deleteSArticle(Integer aid);

    boolean updateSArticle(Integer status, Integer aid);

    int articleSNumber(Integer status);

    //查看次数更新
    int updateSArticleSeeNumber(Integer viewNumber, Integer aid);

    //查询查看次数
    Integer getSArticleViewNumber(Integer aid);

    //查询用户发表文章
    List<Article> listSUserArticle(Integer uid, Integer status);

    List<Article> listByTitle(String title);

    List<Integer> queryByUser(Integer pageSize,Integer pageNum);

    //查询所有的
    List<Article> findAll();

}
