package com.zstao.service.serviceImpl;



import com.zstao.entity.Article;
import com.zstao.mapper.ArticleDao;
import com.zstao.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;
    @Override
    public List<Article> listSArticles(Integer status,Integer pageSize,Integer pageNum) {
        int start=(pageNum-1)*pageSize;
        return articleDao.listArticles(status,start,pageSize);
    }

    @Override
    public Article getSArticle(Integer aid) {
        return articleDao.getArticle(aid);
    }

    @Override
    public boolean saveSArticle(Article article) {
        int i = articleDao.saveArticle(article);
        if(i>0){
            return true;
        }else{

            return false;
        }
    }

    @Override
    public boolean deleteSArticle(Integer aid) {
        int i = articleDao.deleteArticle(aid);
        if(i>0){
            return true;
        }else{

            return false;
        }
    }

    @Override
    public boolean updateSArticle(Integer status, Integer aid) {
        return false;
    }

    @Override
    public int articleSNumber(Integer status) {
        return 0;
    }

    @Override
    public int updateSArticleSeeNumber(Integer viewNumber, Integer aid) {

        return articleDao.updateArticleSeeNumber(viewNumber,aid);
    }

    @Override
    public Integer getSArticleViewNumber(Integer aid) {
        return null;
    }

    @Override
    public List<Article> listSUserArticle(Integer uid, Integer status) {
        return null;
    }

    @Override
    public List<Article> listByTitle(String title) {
        return articleDao.listByTitle(title);
    }

    @Override
    public List<Integer> queryByUser(Integer pageSize, Integer pageNum) {

        int start=(pageNum-1)*pageSize;

        return articleDao.queryByUser(start,pageSize);
    }

    @Override
    public List<Article> findAll() {
        return articleDao.findAll();
    }
}
