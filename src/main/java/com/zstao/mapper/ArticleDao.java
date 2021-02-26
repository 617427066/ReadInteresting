package com.zstao.mapper;

import com.zstao.entity.Article;

import java.util.List;

/**
 * 文章接口
 */
public interface ArticleDao {
    List<Article> listArticles(Integer status,Integer start,Integer end);

    List<Article> findAll();

    Article getArticle(Integer aid);

    int saveArticle(Article article);

    int deleteArticle(Integer aid);

    int updateArticle(Integer status, Integer aid);

    int articleNumber(Integer status);

    //查看次数更新
    int updateArticleSeeNumber(Integer viewNumber, Integer aid);

    //查询查看次数
    Integer getArticleViewNumber(Integer aid);

    //查询用户发表文章
    List<Article> listUserArticle(Integer uid, Integer status);

    //模糊查询
    List<Article> listByTitle(String title);

    List<Integer> queryByUser(int start,int end);
}
