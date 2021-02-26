package com.zstao.controller;

import com.alibaba.fastjson.JSON;
import com.zstao.entity.Comment;
import com.zstao.entity.User;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    /**
     * 查询所有评论
     */
    @ResponseBody
    @PostMapping("/findAllCom")
    public String findAll(HttpServletRequest request){
        String status=request.getParameter("status");
        User admin=(User)request.getSession().getAttribute("admin");
        if(admin!=null){
            if(status!=null&&!status.equals("")){
                List<Comment> byStatus = commentService.findByStatus(Integer.parseInt(status));
                for (Comment comment : byStatus) {
                    switch (comment.getStatus()){
                        case 1:
                            comment.setArticleId("已发表");
                            break;
                        case 2:
                            comment.setArticleId("已删除");
                            break;
                    }
                }
                info.put("code","200");
                info.put("list",byStatus);
                info.put("count",byStatus.size());
            }else{
                List<Comment> all = commentService.findAll();
                for (Comment comment : all) {
                    switch (comment.getStatus()){
                        case 1:
                            comment.setArticleId("已发表");
                            break;
                        case 2:
                            comment.setArticleId("已删除");
                            break;
                    }
                }
                info.put("code","200");
                info.put("list",all);
                info.put("count",all.size());
            }
        }
        return JSON.toJSONString(info);
    }

    /**
     * 删除
     */
    @ResponseBody
    @PostMapping("/deleteComment")
    public String deleteComment(HttpServletRequest request){
        String id = request.getParameter("id");
        User admin=(User)request.getSession().getAttribute("admin");
        if(admin!=null){
            boolean b = commentService.deleteComment(Integer.parseInt(id));
            if(b){
                info.put("msg","success");
            }else{
                info.put("msg","false");
            }
        }
        return JSON.toJSONString(info);
    }
    /**
     *
     * 查询单个
     */
    @ResponseBody
    @GetMapping("/queryCommentOne")
    public String queryCommentOne(String id){
        Comment comment = commentService.getComment(Integer.parseInt(id));
        if (comment!=null) {
            info.put("code","200");
            switch (comment.getStatus()){
                case 1:
                    comment.setArticleId("已发表");
                    break;
                case 2:
                    comment.setArticleId("已删除");
                    break;
            }
            info.put("one",comment);
        }else{
            info.put("code","500");
        }
        return JSON.toJSONString(info);
    }

    /**
     * 发表文章
     * @
     *
     */
    @ResponseBody
    @PostMapping("/toCreateComment")
    public String addCreateComment(HttpServletRequest request){
        String comment=request.getParameter("comment");
        String aid=request.getParameter("aid");
        System.out.println(aid);
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
            Comment comment1 = new Comment();
            comment1.setArticleId(aid);
            comment1.setCMain(comment);
            comment1.setStatus(1);
            Date date = new Date();
            comment1.setCreateDate(new Timestamp(date.getTime()));
            boolean b = commentService.addComment(comment1);
            if(b){
                info.put("code","200");
                info.put("user",user);
            }else{
                info.put("code","500");
            }
        }else{
            info.put("code","404");
        }
        return JSON.toJSONString(info);
    }

}
