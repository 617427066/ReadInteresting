package com.zstao.controller;

import com.alibaba.fastjson.JSON;
import com.zstao.entity.School;
import com.zstao.entity.User;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class SchoolController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(SchoolController.class);
    /**
     * 查询
     * @return
     */
    @GetMapping("/findAllSch")
    @ResponseBody
    public String findAll(){

        List<School> all = schoolService.findAll();
        for (School school : all) {
            List<User> byTime = usi.findByTime(school.getCollege());
            school.setStatus(String.valueOf(byTime.size()));
        }
        info.put("list",all);
        info.put("count",all.size());

        return JSON.toJSONString(info);
    }

    /**
     * 更新
     * @param request
     * @return
     */
    @PostMapping("/updateSch")
    @ResponseBody
    public String updateSch(HttpServletRequest request){
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String number=request.getParameter("number");
        String introduce=request.getParameter("introduce");
        School sch = schoolService.getSch(Integer.parseInt(id));
        sch.setCollege(name);
        sch.setNumber(number);
        sch.setIntroduce(introduce);
        boolean b = schoolService.updateShc(sch);
        if(b){
            info.put("code","200");
            info.put("msg","success");
        }else {
            info.put("code","500");
        }
        return JSON.toJSONString(info);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/deleteSch")
    public String deleteSch(HttpServletRequest request){
        String id = request.getParameter("id");
        boolean b = schoolService.deleteSch(Integer.parseInt(id));
        if(b){
            info.put("code","200");
        }else{
            info.put("cdde","404");
        }
        return JSON.toJSONString(info);
    }
    /**
     * 查询单个
     */
    @PostMapping("/querySch")
    @ResponseBody
    public String querySch(HttpServletRequest request){
        String id = request.getParameter("id");
        School sch = schoolService.getSch(Integer.parseInt(id));
        info.put("one",sch);
        return JSON.toJSONString(info);
    }

    /**
     * 添加
     *
     */
    @ResponseBody
    @PostMapping("/addSch")
    public String addSch(HttpServletRequest request){
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        String introduce=request.getParameter("introduce");
        List<School> all = schoolService.findAll();
        boolean flag=true;
        for (School school : all) {
            if (school.getCollege().equals(name)){
               flag=false;
               break;
            }
        }
        if(flag){
            School school = new School();
            school.setCollege(name);
            school.setIntroduce(introduce);
            school.setNumber(number);
            school.setStatus("1");
            Date date = new Date();
            school.setAddTime(new Timestamp(date.getTime()));
            boolean b = schoolService.saveSch(school);
            if(b){
                info.put("code","200");
            }else{
                info.put("code","500");
            }
        }else{
            info.put("code","404");
            info.put("msg","该学院已存在！");
        }
        return JSON.toJSONString(info);
    }
}
