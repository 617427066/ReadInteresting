package com.zstao.service;

import com.zstao.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    //判断用户权限
    boolean findUserRole(int uid,int rid);

    List<UserRole> findByRole(Integer rid);

    //根据用户id删除用户权限
    boolean deleteRole(Integer user_id);

    //删除用户权限
    boolean deleteUser(Integer id);

    //查询管理员
    List<UserRole> findByAdmin();

    boolean saveRole(UserRole userRole);

    UserRole getOne(Integer id);
}
