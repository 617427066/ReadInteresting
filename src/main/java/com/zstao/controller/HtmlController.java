package com.zstao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class HtmlController {
    /*********************************管理员界面******************************************************/

    //欢迎界面
    @RequestMapping("/xAdmin/welcome")
    public String welcome() {
        return "xAdmin/welcome";
    }
    //管理员首页
    @RequestMapping("/xAdmin/adminIndex")
    public String adminIndex() {
        return "xAdmin/adminIndex";
    }
    //管理员登陆
    @RequestMapping("/xAdmin/adminLogin")
    public String adminLogin() {
        return "xAdmin/adminLogin";
    }

    /**
     * 错误页面
     * @return
     */
    @RequestMapping("/xAdmin/error")
    public String error() {
        return "xAdmin/error";
    }

    /**
     * 用户列表
     */
    @RequestMapping("/xAdmin/member-list")
    public String memberList(){
        return "xAdmin/member-list";
    }

    /**
     * 用户添加
     *
     */
    @RequestMapping("/xAdmin/member-add")
    public String merberadd(){

        return "xAdmin/member-add";
    }
    /**
     * 用户编辑
     */
    @RequestMapping("/xAdmin/member-edit")
    public String merberupdate() {

        return "xAdmin/member-edit";
    }

    /**
     * 修改密码
     */
    @RequestMapping("/xAdmin/member-password")
    public String merberpass(){

        return "xAdmin/member-password";
    }
    /**
     * 院系列表
     */
    @RequestMapping("/xAdmin/school/college-list")
    public String collegelist(){

        return "/xAdmin/school/college-list";
    }

    /**
     * 学院添加页面
     * @return
     */
    @RequestMapping("/xAdmin/school/college-add")
    public String collegeAdd(){
        return "xAdmin/school/college-add";
    }
    /**
     * 学院编辑页面
     * @return
     */
    @RequestMapping("/xAdmin/school/college-edit")
    public String collegeEdit(){
        return "xAdmin/school/college-edit";
    }
    /**
     * 分类管理页面
     *
     */
    @RequestMapping("/xAdmin/school/type-list")
    public String typeList(){
        return "xAdmin/school/type-list";
    }
    @RequestMapping("/xAdmin/school/type-add")
    public String typeAdd(){
        return "xAdmin/school/type-add";
    }
    @RequestMapping("/xAdmin/school/type-edit")
    public String typeEdit(){
        return "xAdmin/school/type-edit";
    }
    /**
     * 管理员
     */
    @RequestMapping("/xAdmin/admin/admin-list")
    public String adminList(){
        return "xAdmin/admin/admin-list";
    }
    @RequestMapping("/xAdmin/admin/admin-add")
    public String adminAdd(){
        return "xAdmin/admin/admin-add";
    }
    @RequestMapping("/xAdmin/admin/admin-edit")
    public String adminEdit(){
        return "xAdmin/admin/admin-edit";
    }
    /**
     * 权限
     */
    @RequestMapping("/xAdmin/admin/role-list")
    public String roleList(){
        return "xAdmin/admin/role-list";
    }
    @RequestMapping("/xAdmin/admin/role-add")
    public String roleAdd(){
        return "xAdmin/admin/role-add";
    }
    @RequestMapping("/xAdmin/admin/role-edit")
    public String roleEdit(){
        return "xAdmin/admin/role-edit";
    }

    /**
     * 系统图标
     */
    @RequestMapping("/xAdmin/unicode")
    public String unicode(){
        return "xAdmin/unicode";
    }

    /**
     * 文章列表
     */
    @RequestMapping("/xAdmin/article/article-list")
    public String articleList(){
        return "xAdmin/article/article-list";
    }
    /**
     * 文章编辑
     */
    @RequestMapping("/xAdmin/article/article-edit")
    public String articleEdit(){
        return "xAdmin/article/article-edit";
    }

    /**
     * 评论列表
     */
    @RequestMapping("/xAdmin/article/comment-list")
    public String commentList(){
        return "xAdmin/article/comment-list";
    }
    /**
     * 评论修改
     */
    @RequestMapping("/xAdmin/article/comment-edit")
    public String commentEdit(){
        return "xAdmin/article/comment-edit";
    }
    /**
     * 折线图
     */
    @RequestMapping("/xAdmin/echarts/echarts1")
    public String echarts1(){
        return "xAdmin/echarts/echarts1";
    }

    /**
     * 饼图
     */
    @RequestMapping("/xAdmin/echarts/echarts4")
    public String echarts4(){
        return "/xAdmin/echarts/echarts4";
    }

    /******************************************用户界面**********************************************/
    //用户首页
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/404")
    public String fail(){
        return "404";
    }

    /**
     * 帮助与反馈页面
     * @return
     */
    @RequestMapping("/help")
    public String help(){
        return "help";
    }

    /**
     * 注册
     */
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 用户主页
     */
    @RequestMapping("/userbolg")
    public String userblog(){
        return "userbolg";
    }

    /**
     * 发表文章
     */
    @RequestMapping("/userEditor")
    public String userEditor(){
        return "userEditor";
    }
    /**
     * 登陆
     */
    @RequestMapping("/userLogin")
    public String userLogin(){
        return "userLogin";
    }
    /**
     * 个人信息页面
     */
    @RequestMapping("/userPersonInfo")
    public String userPersonInfo(){
        return "userPersonInfo";
    }
    /**
     * 用户搜索
     */
    @RequestMapping("/userSearch")
    public String userSearch(){
        return "userSearch";
    }

    /**
     * 查看文章
     */
    @RequestMapping("/userViewArticle")
    public String userViewArticle(){
        return "userViewArticle";
    }
    /**
     * 关注
     */
    @RequestMapping("/userViewLike")
    public String userViewLike(){

        return "userViewLike";
    }
    /**
     * 消息
     */
    @RequestMapping("/userViewInfo")
    public String userViewInfo(){
        return "userViewInfo";
    }
}
