package com.zstao.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSON;
import com.zstao.entity.Role;
import com.zstao.entity.User;
import com.zstao.entity.UserRole;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserRoleController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);
    /**
     * 修改用户权限
     */
     @PostMapping("/updateUserRole")
     @ResponseBody
     public String update(HttpServletRequest request){
         String role=request.getParameter("role");
         String id=request.getParameter("id");
         UserRole one = userRoleService.getOne(Integer.parseInt(id));
         if(one!=null){
             boolean b = userRoleService.deleteUser(one.getId());
             if(b){
                 UserRole role1 = new UserRole();
                 role1.setRoleId(Integer.parseInt(role));
                 role1.setUserId(one.getUserId());
                 boolean b1 = userRoleService.saveRole(role1);
                 if(b1){
                     info.put("code","200");
                 }
             }
         }else{
             info.put("code","500");
         }
         return JSON.toJSONString(info);
     }

    /**
     *查询所有管理员
     *
     *
     **/
    @GetMapping("/findAllAdmin")
    @ResponseBody
    public String findAdmin(){
        List<UserRole> byAdmin = userRoleService.findByAdmin();
        info.put("list",byAdmin);
        info.put("count",byAdmin.size());
        List<Role> roles = roleService.listSRoles();
        info.put("role",roles);
        return JSON.toJSONString(info);
    }

    /**
     * 增加
     */
    @ResponseBody
    @PostMapping("/addUserRole")
    public String addUserRole(HttpServletRequest request){
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        User byPhone = usi.findByPhone(phone);
        if(byPhone!=null){
            UserRole userRole = new UserRole();
            userRole.setUserId(byPhone.getUid());
            userRole.setRoleId(Integer.parseInt(role));
            boolean b = userRoleService.saveRole(userRole);
            if(b){
                info.put("code","200");
            }else{
                info.put("code","500");
            }
        }else {
            info.put("code","404");
        }
        return JSON.toJSONString(info);
    }

    /**
     * 查询权限名称
     *
     */
    @PostMapping("/queryRole")
    @ResponseBody
    public String queryRole(){
        List<Role> roles = roleService.listSRoles();
        info.put("role",roles);
        return JSON.toJSONString(info);
    }

    /**
     * 根据权限查询
     */
    @ResponseBody
    @PostMapping("/queryUserByRole")
    public String queryByRole(HttpServletRequest request){
        String rid = request.getParameter("rid");
        List<UserRole> byRole = userRoleService.findByRole(Integer.parseInt(rid));
        info.put("list",byRole);
        info.put("count",byRole.size());
        return JSON.toJSONString(info);
    }
    /**
     * 查询单个
     */
    @ResponseBody
    @PostMapping("/findUserRoleOne")
    public String findUserRole(HttpServletRequest request){
        String id=request.getParameter("id");
        UserRole one = userRoleService.getOne(Integer.parseInt(id));
        info.put("user",one);
        List<Role> roles = roleService.listSRoles();
        info.put("role",roles);
        return JSON.toJSONString(info);
    }
    /**
     * 删除
     */
    @ResponseBody
    @PostMapping("/deleteUserRole")
    public String delete(HttpServletRequest request){
        String id = request.getParameter("id");
        boolean b = userRoleService.deleteUser(Integer.parseInt(id));
        if(b){
            info.put("code","200");
        }else{
            info.put("code",500);
        }
        return JSON.toJSONString(info);
    }

}
