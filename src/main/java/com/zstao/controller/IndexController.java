package com.zstao.controller;

import com.alibaba.fastjson.JSON;
import com.zstao.entity.*;
import com.zstao.service.ArticleService;
import com.zstao.service.UserService;
import com.zstao.util.DateTime;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.lang.model.element.VariableElement;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    /**
     * 首页查询
     * @param request
     * @return
     */
    @PostMapping("/indexArticle")
    @ResponseBody
    public String queryArticle(HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        if(user!=null){
            info.put("user",user);
        }else{
            info.put("user",null);
        }
        List<Article> articles = articleService.listSArticles(1,10,1);
        for (Article article : articles) {
            List<Comment> byArticle = commentService.findByArticle(article.getAid());
            if(byArticle.isEmpty()){
                article.setLikeNumber("0");
            }else{

                article.setLikeNumber(String.valueOf(byArticle.size()));
            }
        }
        info.put("list",articles);

        List<School> all = schoolService.findAll();
        info.put("school",all);
        ArrayList<User> users = new ArrayList<>();
        List<Integer> integers = articleService.queryByUser(6, 1);

        for (int i=0; i < integers.size();i++){
            User sUser = usi.getSUser(integers.get(i));
            int userBeLikeNumber = userLikeService.getUserBeLikeNumber(sUser.getUid());
            if(user!=null){
                UserLike userLike = userLikeService.queryByUser(user.getUid(), sUser.getUid());
                if(userLike!=null){
                    sUser.setEmail("1");
                }else{
                    sUser.setEmail("2");
                }
            }else{
                sUser.setEmail("2");
            }
            sUser.setPhone(String.valueOf(userBeLikeNumber));
            users.add(sUser);
        }
        info.put("users",users);
        return JSON.toJSONString(info);
    }

    /**
     * 文章分页
     */
    @PostMapping("/findLimitArticle")
    @ResponseBody
    public String findLimitArticle(HttpServletRequest request){
        String pageSize = request.getParameter("pageSize");
        String pageNum=request.getParameter("pageNum");
        List<Article> articles = articleService.listSArticles(1,Integer.parseInt(pageSize),Integer.parseInt(pageNum));
        if(articles.isEmpty()){
            info.put("code","500");
        }else{
            info.put("list",articles);
            info.put("code","200");
        }
        return JSON.toJSONString(info);
    }

    /**
     * 作者分页
     */
    @PostMapping("/findLimitAuthor")
    @ResponseBody
    public String findLimitAuthor(HttpServletRequest request) {
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        List<User> users = new ArrayList<>();
        List<Integer> integers = articleService.queryByUser(6, 1);
        if (integers.isEmpty()) {
            info.put("code","500");
        } else {
        info.put("code","200");
        for (int i = 0; i < integers.size(); i++) {
            User sUser = usi.getSUser(integers.get(i));
            int userBeLikeNumber = userLikeService.getUserBeLikeNumber(sUser.getUid());
            sUser.setPhone(String.valueOf(userBeLikeNumber));
            users.add(sUser);
        }
        info.put("users",users);
    }

        return JSON.toJSONString(info);
    }


    /**
     * 首页搜索
     */
    @ResponseBody
    @PostMapping("/queryInfo")
    public String findSearch(HttpServletRequest request){
        String name=request.getParameter("name");
        List<Article> articles = articleService.listByTitle(name);
        info.put("article",articles);
        List<User> byTime = usi.findByTime(name);
        info.put("users",byTime);

        return JSON.toJSONString(info);
    }

    /**
     * 用户动态
     */
    @PostMapping("/findUserInfos")
    @ResponseBody
    public String findUserInfos(HttpServletRequest request) throws ParseException {
        User user= (User) request.getSession().getAttribute("user");
        if(user!=null){
            ArrayList<UserInfo> userInfos = new ArrayList<>();
            User sUser = usi.getSUser(user.getUid());
            List<Article> articles = articleService.listSUserArticle(user.getUid(), 1);
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
            //未完待续  评论
            List<Comment> byUser = commentService.findByUser(user.getUid());
            for (Comment comment : byUser) {
                UserInfo userInfo1 = new UserInfo();
                userInfo1.setDate(DateTime.TimestampToStringCHN(comment.getCreateDate()));
                userInfo1.setInfo("对文章："+comment.getTitle()+"发表了自己的看法");
                userInfo1.setName(Integer.parseInt(comment.getArticleId()));
                userInfos.add(userInfo1);
            }
            info.put("list",userInfos);
        }
        return JSON.toJSONString(info);
    }

    /**
     * 用户消息
     */
    @ResponseBody
    @PostMapping("/queryUserMessage")
    public String queryUserMessage(HttpServletRequest request) throws ParseException {
        User user= (User) request.getSession().getAttribute("user");
        if(user!=null){
            ArrayList<UserInfo> userInfos = new ArrayList<>();
            List<Article> articles = articleService.listSUserArticle(user.getUid(), 1);
            for (Article article : articles) {
                List<Comment> byArticle = commentService.findByArticle(article.getAid());
                for (Comment comment : byArticle) {
                    UserInfo info = new UserInfo();
                    info.setTitle(comment.getUserId());
                    info.setInfo(comment.getArticleId());
                    info.setDate(DateTime.TimestampToStringCHN(comment.getCreateDate()));
                    userInfos.add(info);

                }
            }
            info.put("info",userInfos);
        }
        return JSON.toJSONString(info);
    }

    /**
     * 关注用户
     *
     */
    @ResponseBody
    @PostMapping("/attentionAuthor")
    public String attentionAuthor(HttpServletRequest request){
        String id=request.getParameter("uid");
        String status=request.getParameter("status");
        User user= (User) request.getSession().getAttribute("user");
        if(user!=null){
            UserLike userLike = userLikeService.queryByUser(user.getUid(), Integer.parseInt(id));
            if(userLike!=null){
                userLike.setStatus(Integer.parseInt(status));
                int update = userLikeService.update(userLike);
                if(update>0){
                    info.put("code","200");
                }else{
                    info.put("code","400");
                }
            }else{
                UserLike like = new UserLike();
                like.setStatus(1);
                like.setAuthorId(id);
                like.setUserId(String.valueOf(user.getUid()));
                Date date = new Date();
                like.setCreateDate(new Timestamp(date.getTime()));
                int add = userLikeService.add(like);
                if(add>0){
                    info.put("code","200");
                }else{
                    info.put("code","400");
                }
            }
        }
        return JSON.toJSONString(info);
    }


}
