package com.zstao.service;


import com.zstao.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> listSRoles();

    Role getSRoleName(Integer rid);

    boolean saveSRole(Role role);

    boolean deleteSRole(Integer rid);
}
