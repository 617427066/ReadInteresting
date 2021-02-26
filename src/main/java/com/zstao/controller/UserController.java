package com.zstao.controller;

import com.alibaba.fastjson.JSON;
import com.zstao.entity.NumberWelcome;
import com.zstao.entity.User;
import com.zstao.service.UserRoleService;
import com.zstao.service.UserService;

import com.zstao.util.DateTime;
import com.zstao.util.JsonResult;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller

public class UserController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * 管理员登陆
     */
    @GetMapping(value = "/findAdminLogin",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String finAdminLogin(String phone,String password,HttpServletRequest request){
        Map<String, Object> infos = new HashMap<>();
        User login;
        try {
            login = usi.userSVerify(phone, password);

        } catch (Exception e) {
            infos.put("code", 404);
            infos.put("msg", e.getMessage());

            return JSON.toJSONString(infos);
        }
        if (login != null) {
            if(userRoleService.findUserRole(login.getUid(),1)){
              infos.put("code", 200);
                infos.put("msg", "success");
                infos.put("uid", login.getUid());
                HttpSession session=request.getSession();
                session.setAttribute("admin",login);

                session.setMaxInactiveInterval(-1);
                return JSON.toJSONString(infos);
            }else{
                infos.put("code", 200);
                infos.put("msg", "role");
                infos.put("uid", login.getUid());
               return JSON.toJSONString(infos);
            }

        } else {
            infos.put("code", 500);
            infos.put("msg", "fail");
            return JSON.toJSONString(infos);
        }
    }
    /**
     * 界面显示用户信息
     */
    @PostMapping("/findUser")
    @ResponseBody
    public String findUser(HttpServletRequest request){
        Map<String, Object> infos = new HashMap<>();
        User admin = (User)request.getSession().getAttribute("admin");
        if(admin!=null){
            User user=usi.getSUser(admin.getUid());
            infos.put("code",200);
            infos.put("user",user);
        }else{
            infos.put("code",500);
            infos.put("user",null);
        }

        return JSON.toJSONString(infos);
    }

    /**
     * 管理员登陆界面显示参数
     * @return
     */
//    @ApiOperation(value = "显示首页数量",notes = "显示几个参数")
    @GetMapping("/getNumberWelcome")
    @ResponseBody
    public ResponseEntity getNumberWelcome(){
        NumberWelcome nw=usi.getSNumber();
        return ResponseEntity.ok(nw);
    }

    /**
     * 用户注册
     */
//    @ApiOperation(value = "注册",notes = "用户注册")
    @PostMapping(value = "/userRegister")
    @ResponseBody
    public ResponseEntity UserRegister(String username,String phone,String password){
        JsonResult result = new JsonResult();

        boolean flag = usi.userSRegister(password,phone, username);

        result.setCode(200);
        result.setData(flag);

        return ResponseEntity.ok(result);
    }
    /**
     * 记住密码
     */
    @ResponseBody
    @PostMapping("/toLoginCookie")
    public String toLoginCookie(HttpServletRequest request) {

        String username = "";
        String password = "";
        String s;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {

            for (Cookie cookie : cookies) {
                s = cookie.getName(); // 通过getName方法获得cookie的名称
                if (s != null && s != "") {
                    if (s.equals("pername")) {
                        if (cookie.getValue() != null) {
                            username = username + cookie.getValue(); // 通过getValue方法获得cookie的值
                            info.put("pername", username);
                        }
                    } else if (s.equals("perpass")) {
                        if (cookie.getValue() != null) {
                            password = password + cookie.getValue();
                            info.put("perpass", password);
                        }
                    }
                }

            }

        }

        return JSON.toJSONString(info);
    }

    /**
     * 用户登陆验证
     */
    @PostMapping(value = "/userLogin")
    @ResponseBody
    public String userLogin(HttpServletRequest request, HttpServletResponse response){
        String checked = request.getParameter("checked");
        String phone=request.getParameter("phone");
        String password=request.getParameter("password");
        Map<String, Object> infos = new HashMap<>();
        User login;
        try {
            login = usi.userSVerify(phone, password);
        } catch (Exception e) {
            infos.put("code", 404);
            infos.put("msg", e.getMessage());

            return JSON.toJSONString(infos);
        }

        if (login != null) {
            if(userRoleService.findUserRole(login.getUid(),2)){
                if (checked.equals("1")) {
                    // 记住密码:生成新的cookie用来保存账号密码
                    Cookie username = new Cookie("pername", phone);
                    username.setMaxAge(1296000);// 设置cookie最长保存时间15天
                    Cookie password1 = new Cookie("perpass", password);
                    password1.setMaxAge(1296000);
                    // 覆盖旧的cookie
                    response.addCookie(username);
                    response.addCookie(password1);
                } else {

                    Cookie[] cookies = request.getCookies();
                    Cookie username = null;
                    Cookie password1 = null;
                    // 寻找是否已经存在cookie
                    if (cookies == null) {
                    } else {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("pername")) {
                                username = cookie;
                            } else if (cookie.getName().equals("perpass")) {
                                password1 = cookie;
                            }
                        }
                        // 若cookie存在则通过设置cookie保存时间为0的方法来删除cookie
                        if (username != null) {
                            username.setMaxAge(0);
                            response.addCookie(username);
                        }
                        if (password1 != null) {
                            password1.setMaxAge(0);
                            response.addCookie(password1);
                        }
                    }
                }

                infos.put("code", 200);
                infos.put("msg", "success");
                infos.put("uid", login.getUid());
                request.getSession().setAttribute("user",login);
                request.getSession().setMaxInactiveInterval(-1);
                return JSON.toJSONString(infos);
            }else{
                infos.put("code", 200);
                infos.put("msg", "role");

                return JSON.toJSONString(infos);
            }

        } else {
            infos.put("code", 500);
            infos.put("msg", "fail");
            return JSON.toJSONString(infos);
        }
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/findAllUser")
    public String findAllUser() throws ParseException {
        List<User> users = usi.listSUsers();
        int number = usi.findListNumber();

        info.put("list",users);
        info.put("number",number);
        return JSON.toJSONString(info);
    }

    /**
     * 更新用户信息
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/updateUser")
    public String updateUser(HttpServletRequest request){

        String nickname = request.getParameter("nickname");
        String name=request.getParameter("name");
        String phone=request.getParameter("phone");
        String email=request.getParameter("email");
        String id=request.getParameter("id");
        String introduce=request.getParameter("introduce");
        String password=request.getParameter("password");
        User user = usi.getSUser(Integer.parseInt(id));
        if(user!=null){
            user.setPassword(password);
            user.setPhone(phone);
            user.setEmail(email);
            user.setIntroduce(introduce);
            user.setNickname(nickname);
            user.setUname(name);

            boolean b = usi.updateSUSer(user);
            if(b){
                info.put("code","200");
            }else {
                info.put("code","500");
            }
        }
        return JSON.toJSONString(info);
    }
    /**
     * 修改密码
     */
    @PostMapping("/updatePass")
    @ResponseBody
    public String updatePass(HttpServletRequest request){

        return null;
    }

    /**
     * 删除用户
     */
    @ResponseBody
    @PostMapping(value = "/deleteUser")
    public String deleteUser(String id){
        boolean flag = usi.deleteSUser(Integer.parseInt(id));
        boolean b = userRoleService.deleteRole(Integer.parseInt(id));
        Map<String, Object> infos = new HashMap<>();
        if(flag && b){
            infos.put("code","200");
            infos.put("msg","删除成功！");

        }else{
            infos.put("code","500");
            infos.put("msg","删除失败！");
        }
        return JSON.toJSONString(infos);
    }

    /**
     * 查询单个用户
     *
     */
    @PostMapping(value = "/queryUser")
    @ResponseBody
    public String queryUser(HttpServletRequest request){
        String id = request.getParameter("id");
        User user = (User) request.getSession().getAttribute("user");
        if(id.equals("")||id==null){
            User sUser = usi.getSUser(user.getUid());
            info.put("user1",sUser);
        }else{

            User sUser = usi.getSUser(Integer.parseInt(id));
            info.put("user1",sUser);
        }

        return JSON.toJSONString(info);
    }
    /**
     * 用户管理
     *   模糊查询
     * @author 赵胜涛
     *
     */
    @PostMapping("/queryUserByTime")
    @ResponseBody
    public String queryBytime(HttpServletRequest request){
        String sch=request.getParameter("name");
        List<User> list = usi.findByTime(sch);
        info.put("list",list);
        info.put("number",list.size());
        return JSON.toJSONString(info);
    }

    /**
     * 添加用户
     *
     */
    @PostMapping(value = "/addUser")
    @ResponseBody
    public String addUser(HttpServletRequest request){
        String phone=request.getParameter("phone");
        String nickname=request.getParameter("nickname");
        String password=request.getParameter("password");
        boolean b = usi.userSRegister(password, phone, nickname);
        if(b){
            info.put("code","200");
        }else{
            info.put("code","500");
        }

        return JSON.toJSONString(info);
    }

    /**
     * 用户退出
     */
    @PostMapping("/userExit")
    @ResponseBody
    public String userExit(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
            request.getSession().setMaxInactiveInterval(0);
            info.put("code","200");
        }
        return JSON.toJSONString(info);
    }


    /**
     * 用户更新信息1
     */
    @PostMapping("/userUpdateInfo1")
    @ResponseBody
    public String userUpdateInfo1(HttpServletRequest request){
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
            User sUser = usi.getSUser(user.getUid());
            sUser.setPhone(phone);
            sUser.setEmail(email);
            sUser.setNickname(nickname);
            sUser.setPassword(password);
            boolean b = usi.updateSUSer(sUser);
            if(b){
                info.put("code","200");
            }else{
                info.put("code","500");
            }
        }
        return JSON.toJSONString(info);
    }

    /**
     * 用户更新信息1
     */
    @PostMapping("/userUpdateInfo2")
    @ResponseBody
    public String userUpdateInfo2(HttpServletRequest request){
        String uname = request.getParameter("name");
        String introduce = request.getParameter("introduce");
        String sex = request.getParameter("sex");
        String number = request.getParameter("number");
        String college = request.getParameter("college");
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
            User sUser = usi.getSUser(user.getUid());
            sUser.setUname(uname);
            sUser.setIntroduce(introduce);
            sUser.setSex(sex);
            sUser.setNumber(number);
            sUser.setSch_id(college);
            boolean b = usi.updateSUSer(sUser);
            if(b){
                info.put("code","200");
            }else{
                info.put("code","500");
            }
        }
        return JSON.toJSONString(info);
    }
}
