package com.zstao.service.serviceImpl;

import com.zstao.entity.Role;
import com.zstao.mapper.RoleDao;
import com.zstao.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public List<Role> listSRoles() {
        return roleDao.listRoles();
    }

    @Override
    public Role getSRoleName(Integer rid) {
        return roleDao.getRoleName(rid);
    }

    @Override
    public boolean saveSRole(Role role) {
        if(roleDao.saveRole(role)!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteSRole(Integer rid) {
        if(roleDao.deleteRole(rid)!=0){
            return true;
        }else{
            return false;
        }
    }
}
