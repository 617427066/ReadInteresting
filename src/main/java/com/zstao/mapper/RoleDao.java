package com.zstao.mapper;


import com.zstao.entity.Role;

import java.util.List;

/**
 * 权限接口
 */
public interface RoleDao {
    List<Role> listRoles();

    Role getRoleName(Integer rid);

    int saveRole(Role role);

    int deleteRole(Integer rid);


}
