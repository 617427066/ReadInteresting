package com.zstao.service.serviceImpl;



import com.zstao.entity.ArticleCollect;
import com.zstao.mapper.ArticleCollectDao;
import com.zstao.service.ArticleCollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ArticleCollectServiceImpl implements ArticleCollectService {

    @Resource
    private ArticleCollectDao articleCollectDao;

    @Override
    public List<ArticleCollect> listSArticleCollects(Integer status) {
        return articleCollectDao.listArticleCollects(status);
    }

    @Override
    public boolean saveSArticleCollect(ArticleCollect articleCollect) {
        if(articleCollectDao.saveArticleCollect(articleCollect)!=0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean deleteSArticleCollect(Integer collectId) {
        return false;
    }

    @Override
    public boolean updateSArticleCollect(Integer status, Integer collectId) {
        return false;
    }

    @Override
    public int articleCollectSNumber() {
        return articleCollectDao.ArticleCollectNumber();
    }

    @Override
    public List<ArticleCollect> listSArticleBeCollects(Integer articleId) {

        return articleCollectDao.listArticleBeCollects(articleId);
    }

    @Override
    public List<ArticleCollect> listSUserCollectArticles(Integer uid, Integer status) {

        return articleCollectDao.listUserCollectArticles(uid,status);
    }
}
