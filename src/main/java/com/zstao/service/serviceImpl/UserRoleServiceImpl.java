package com.zstao.service.serviceImpl;

import com.zstao.entity.UserRole;
import com.zstao.mapper.UserRoleDao;
import com.zstao.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleDao userRoleDao;
    @Override
    public boolean findUserRole(int uid, int rid) {
        List<UserRole> list=userRoleDao.listUserRoleInfo(uid);
        boolean flag=false;
        for (UserRole role:
             list) {
            if (role.getRoleId()==rid||role.equals(rid)){
                flag=true;
                break;
            }
        }
        return flag;
    }

    @Override
    public List<UserRole> findByRole(Integer rid) {
        return userRoleDao.listRoleBeUsers(rid);
    }

    @Override
    public boolean deleteRole(Integer user_id) {
        if(userRoleDao.deleteRole(user_id)!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteUser(Integer id) {
        if(userRoleDao.deleteUserRole(id)!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<UserRole> findByAdmin() {

        return userRoleDao.findRoleByAdmin();
    }

    @Override
    public boolean saveRole(UserRole userRole) {
        if(userRoleDao.saveUserRole(userRole)!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public UserRole getOne(Integer id) {
        return userRoleDao.getUserRole(id);
    }


}
