package com.zstao.mapper;


import com.zstao.entity.User;

import java.sql.Timestamp;
import java.util.List;

/**
 * 用户的接口
 *
 * @author 赵胜涛
 */
public interface UserDao {

    // 查询所有的用户
    List<User> listUsers();

    //查询单个用户的相信信息
    User getUser(Integer id);

    //删除用户
    int deleteUser(int id);

    //增加用户
    int saveUser(User user);

    //修改用户信息
    int updateUser(User user);

    // 查询用户数量
    int userNumber();

    //验证账户密码,用户登录
    User userVerify(String phone, String password);

    //用户注册
    int userRegister(String password,String phone, String nickname);

    //通过用户电话查询用户
    User getUserByPhone(String phone);

    //查询用户 模糊查询

    List<User> findByTime(String sch);
}
