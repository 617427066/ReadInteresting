package com.zstao.service.serviceImpl;


import com.zstao.entity.ArticleType;
import com.zstao.mapper.ArticleTypeDao;
import com.zstao.service.ArticleTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {

    @Resource
    private ArticleTypeDao articleTypeDao;

    @Override
    public List<ArticleType> listArticleTypes() {
        return articleTypeDao.listArticleTypes();
    }

    @Override
    public String getArticleType(Integer atypeId) {
        return articleTypeDao.getArticleType(atypeId);
    }

    @Override
    public int articleTypeNumber() {
        return articleTypeDao.articleTypeNumber();
    }

    @Override
    public boolean saveArticleType(ArticleType articleType) {
        if(articleTypeDao.saveArticleType(articleType)!=0){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean deleteArticleType(Integer atypeId) {
        if(articleTypeDao.deleteArticleType(atypeId)!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateArticleType(ArticleType articleType) {
        if(articleTypeDao.updateArticleType(articleType)!=0){
            return true;
        }else {
            return false;
        }
    }
}
