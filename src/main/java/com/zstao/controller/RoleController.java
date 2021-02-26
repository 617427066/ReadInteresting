package com.zstao.controller;

import com.alibaba.fastjson.JSON;
import com.zstao.entity.Role;
import com.zstao.entity.User;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RoleController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    /**
     * 查询所有权限
     *
     */
    @GetMapping("/findRoles")
    @ResponseBody
    public String findAllRole(HttpServletRequest request){
        User admin = (User) request.getSession().getAttribute("admin");
        if(admin!=null){
            List<Role> roles = roleService.listSRoles();
            info.put("list",roles);
            info.put("count",roles.size());
        }
        return JSON.toJSONString(info);
    }

    /**
     * 删除权限
     *
     */
    @PostMapping("/deleteRole")
    @ResponseBody
    public String deleteRole(HttpServletRequest request){
        String id = request.getParameter("id");
        User admin = (User) request.getSession().getAttribute("admin");
        if(admin!=null){
            boolean b = roleService.deleteSRole(Integer.parseInt(id));
            if(b){
                info.put("msg","success");
            }else{
                info.put("msg","false");
            }
        }
        return JSON.toJSONString(info);
    }
    /**
     * 增加权限
     */
    @PostMapping("/addRole")
    @ResponseBody
    public String addRole(HttpServletRequest request){
        User admin = (User) request.getSession().getAttribute("admin");
        String name = request.getParameter("name");
        String content=request.getParameter("content");
        Role role = new Role();
        role.setRname(name);
        role.setContent(content);
        boolean b = roleService.saveSRole(role);
        if(b){
            info.put("code","200");
        }else{
            info.put("code","500");
        }
        return JSON.toJSONString(info);
    }

    /**
     * 更新权限
     */
    @PostMapping("/updateRole")
    @ResponseBody
    public String updateRole(HttpServletRequest request){
        String id = request.getParameter("id");
        String rname=request.getParameter("rname");
        String content = request.getParameter("content");
        Role sRoleName = roleService.getSRoleName(Integer.parseInt(id));
        if(sRoleName!=null){
            sRoleName.setRname(rname);
            sRoleName.setContent(content);
            boolean b = roleService.saveSRole(sRoleName);
            if(b){
                info.put("msg","success");
            }else{
                info.put("msg","false");
            }
        }
        return JSON.toJSONString(info);
    }
    /**
     * 查询权限
     */
    @PostMapping("/queryRoles")
    @ResponseBody
    public String queryRole(HttpServletRequest request) {
        String id = request.getParameter("id");
        Role sRoleName = roleService.getSRoleName(Integer.parseInt(id));
        if(sRoleName!=null){
            info.put("code","200");
            info.put("one",sRoleName);
        }else{
            info.put("code","500");
        }
        return JSON.toJSONString(info);
    }
}
