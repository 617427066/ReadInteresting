package com.zstao.controller;

import com.alibaba.fastjson.JSON;
import com.zstao.entity.*;
import com.zstao.util.DateTime;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserBlogController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserBlogController.class);
    @PostMapping("/findUserBolg")
    @ResponseBody
    public String findUserBolg(HttpServletRequest request) throws ParseException {
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
            List<Article> articles = articleService.listSUserArticle(user.getUid(), 1);
            user.setEmail(String.valueOf(articles.size())); //文章数

            info.put("article",articles);
            ArrayList<UserInfo> userInfos = new ArrayList<>();
            UserInfo userInfo = new UserInfo();
            userInfo.setInfo("注册了该账号");
            userInfo.setDate(DateTime.TimestampToStringCHN(user.getCreateDate()));
            userInfos.add(userInfo);
            for (Article article : articles) {
                UserInfo userInfo1 = new UserInfo();
                userInfo1.setDate(DateTime.TimestampToStringCHN(article.getCreateDate()));
                userInfo1.setInfo("发表了一篇文章");
                userInfo1.setName(article.getAid());
                userInfos.add(userInfo1);
            }
            List<Comment> byUser = commentService.findByUser(user.getUid());
            for (Comment comment : byUser) {
                UserInfo userInfo2 = new UserInfo();
                userInfo2.setDate(DateTime.TimestampToStringCHN(comment.getCreateDate()));
                userInfo2.setInfo("对文章："+comment.getTitle()+"发表了自己的看法");
                userInfo2.setName(Integer.parseInt(comment.getArticleId()));
                userInfos.add(userInfo2);
            }
            int userBeLikeNumber = userLikeService.getUserBeLikeNumber(user.getUid());
            user.setPhone(String.valueOf(userBeLikeNumber)); //粉丝
            int userLikeNumber = userLikeService.getUserLikeNumber(user.getUid());
            user.setUname(String.valueOf(userLikeNumber));//关注
            List<ArticleCollect> articleCollects = articleCollectService.listSUserCollectArticles(user.getUid(), 1);
            user.setSex(String.valueOf(articleCollects.size()));//收藏
            info.put("collect",articleCollects);
            info.put("user",user);
        }

        return JSON.toJSONString(info);
    }

    

}
