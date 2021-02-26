package com.zstao.controller;

import com.alibaba.fastjson.JSON;
import com.zstao.entity.Article;
import com.zstao.entity.ArticleCollect;
import com.zstao.entity.Comment;
import com.zstao.entity.User;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ArticleController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    /**
     *
     * 根据状态查询全部文章
     */
    @PostMapping("/findAllArticle")
    @ResponseBody
    public String findAllArticle(HttpServletRequest request){
        User admin = (User) request.getSession().getAttribute("admin");
        String status=request.getParameter("status");
        if(admin!=null){
            if(status!=null&&!status.equals("")){
                List<Article> articles = articleService.listSArticles(Integer.parseInt(status),100,1);
                for (Article article : articles) {
                    List<ArticleCollect> articleCollects = articleCollectService.listSArticleBeCollects(article.getAid());
                    switch (article.getStatus()){
                        case 1:
                            article.setAtypeId("已发表");
                            break;
                        case 2:
                            article.setAtypeId("已删除");
                            break;
                        default:
                            article.setAtypeId("草稿");
                    }
                    article.setLikeNumber(String.valueOf(articleCollects.size()));
                    List<Comment> byArticle = commentService.findByArticle(article.getAid());
                    article.setCommentNum(byArticle.size());
                }
                info.put("list",articles);
                info.put("count",articles.size());
                info.put("code","200");
            }else{
                List<Article> all = articleService.findAll();
                for (Article article : all) {
                    List<ArticleCollect> articleCollects = articleCollectService.listSArticleBeCollects(article.getAid());
                    article.setLikeNumber(String.valueOf(articleCollects.size()));
                    List<Comment> byArticle = commentService.findByArticle(article.getAid());
                    article.setCommentNum(byArticle.size());
                    switch (article.getStatus()){
                        case 1:
                            article.setAtypeId("已发表");
                            break;
                        case 2:
                            article.setAtypeId("已删除");
                            break;
                        default:
                            article.setAtypeId("草稿");
                    }
                }
                info.put("list",all);
                info.put("count",all.size());
                info.put("code","200");
            }

        }else {
                info.put("code","500");
        }
        return JSON.toJSONString(info);
    }


    /**
     * 删除文章
     */
    @ResponseBody
    @PostMapping("/deleteArticle")
    public String deleteArticle(HttpServletRequest request){
        String id = request.getParameter("id");
        boolean b = articleService.deleteSArticle(Integer.parseInt(id));
        if(b){
            info.put("flag","success");
        }else{
            info.put("flag","false");
        }
        return JSON.toJSONString(info);

    }

    /**
     * 查询单个文章
     */
    @PostMapping("/queryArticleadmin")
    @ResponseBody
    public String queryArticle(HttpServletRequest request){
        String id=request.getParameter("id");
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
            info.put("user",user);
        }
        Article sArticle = articleService.getSArticle(Integer.parseInt(id));
        info.put("article",sArticle);
        if(sArticle!=null){
            User sUser = usi.getSUser(Integer.parseInt(sArticle.getUserId()));
            info.put("author",sUser);
            List<Comment> comments = commentService.findByArticle(sArticle.getAid());
            info.put("comment",comments);
            
        }
        return JSON.toJSONString(info);
    }

    /**
     * 添加文章
     */
    @ResponseBody
    @PostMapping("/addArticle")
    public String addArticle(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        String title = request.getParameter("title");
        String content=request.getParameter("content");
        if(user!=null){
            Article article = new Article();
            article.setAMain(content);
            article.setStatus(1);
            article.setTitle(title);
            article.setAtypeId("1");
            article.setUserId(String.valueOf(user.getUid()));
            article.setViewNumber(0);
            boolean b = articleService.saveSArticle(article);
            if(b){
                info.put("flag","success");
            }else{
                info.put("flag","false");
            }
        }

        return JSON.toJSONString(info);
    }
    /**
     * 发表文章
     */
    @ResponseBody
    @PostMapping("/updateArticle")
    public String updateArticle(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        String id=request.getParameter("id");
        String status=request.getParameter("status");
        if(user!=null){
            Article article = articleService.getSArticle(Integer.parseInt(id));
            article.setStatus(Integer.parseInt(status));
            boolean b = articleService.saveSArticle(article);
            if(b){
                info.put("flag","success");
            }else{
                info.put("flag","false");
            }
        }

        return JSON.toJSONString(info);
    }

    /**
     * 用户查看自己文章
     */
    @ResponseBody
    @GetMapping("/queryArticleByUser")
    public String queryArticleByUser(HttpServletRequest request){
        String status = request.getParameter("status");
        User user=(User)request.getSession().getAttribute("user");
        if(user!=null){
            List<Article> articles = articleService.listSUserArticle(user.getUid(), Integer.parseInt(status));
            info.put("list",articles);
        }
        return JSON.toJSONString(info);
    }

    /**
     * 查询草稿
     */
    @ResponseBody
    @PostMapping("/queryNotArticle")
    public String queryNotArticle(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if(user!=null){
            List<Article> list = articleService.listSUserArticle(user.getUid(), 0);
            System.out.println(list);
            info.put("list",list);
        }
        return JSON.toJSONString(info);
    }

    /**
     * echarts
     * 饼图
     */
    @ResponseBody
    @PostMapping("/findBySchoolEcharts")
    public String findBySchool(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if(user!=null){
            List<Article> all = articleService.findAll();

            for (Article article : all) {

            }
        }
        return JSON.toJSONString(info);
    }


}
