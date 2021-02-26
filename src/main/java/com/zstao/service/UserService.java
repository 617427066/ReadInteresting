package com.zstao.service;

import com.zstao.entity.NumberWelcome;
import com.zstao.entity.User;

import java.util.List;

public interface UserService {

    List<User> listSUsers();

    User getSUser(int id);

    boolean saveSUser(User user);

    boolean deleteSUser(Integer id);

    boolean updateSUSer(User user);

    NumberWelcome getSNumber();

    int findListNumber();
    //用户登录验证
    User userSVerify(String phone, String password);

    //用户注册
    boolean userSRegister(String password,String phone, String nickname);

    List<User> findByTime(String sch);

    //根据手机号查询用户
    User findByPhone(String phone);

}
