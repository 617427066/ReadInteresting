package com.zstao.service.serviceImpl;


import com.zstao.entity.NumberWelcome;
import com.zstao.entity.User;
import com.zstao.entity.UserRole;
import com.zstao.mapper.*;
import com.zstao.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private ArticleDao article;

    @Resource
    private CommentDao commentDao;
    @Resource
    private ArticleTypeDao articleTypeDao;
    @Override
    public List<User> listSUsers() {
        //PageHelper.startPage(pageNum, pageSize);
        return userDao.listUsers();
    }

    /**
     * 查询用户具体信息
     * 包含用户信息，
     * 关注信息，发表文章信息
     * 喜欢类型
     * @param id
     * @return
     */
    @Override
    public User getSUser(int id) {
        return userDao.getUser(id);
    }


    @Override
    public boolean saveSUser(User user) {
        if(userDao.saveUser(user)!=0){
            return true;
        }else{
        return false;
        }
    }

    @Override
    public boolean deleteSUser(Integer id) {
        if(userDao.deleteUser(id)!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateSUSer(User user) {
        if(userDao.updateUser(user)!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public NumberWelcome getSNumber() {
        NumberWelcome numberWelcome = new NumberWelcome();
        numberWelcome.setUserNumber(userDao.userNumber());
        numberWelcome.setArticleNumber(article.articleNumber(1));
        numberWelcome.setCommentNumber(commentDao.commentNumber());
        numberWelcome.setTypeNumber(articleTypeDao.articleTypeNumber());
        return numberWelcome;
    }

    @Override
    public int findListNumber() {
        return userDao.userNumber();
    }

    /**
     * 用户登录验证
     * @param phone
     * @param password
     * @return
     */
    @Override
    public User userSVerify(String phone, String password) {
        User user = userDao.userVerify(phone,password);
        return user;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public boolean userSRegister(String password,String phone, String nickname) {

        User user = userDao.getUserByPhone(phone);
        if (user != null) {
            return false;
        } else {
            if (userDao.userRegister(password,phone, nickname) != 0) {

                UserRole userRole = new UserRole();
                //1是管理员权限 2是用户权限
                userRole.setRoleId(2);
                userRole.setUserId(userDao.getUserByPhone(phone).getUid());
                int i = userRoleDao.saveUserRole(userRole);
                if (i != 0) {
                    return true;
                }
            }
        }
        return false;

    }

    @Override
    public List<User> findByTime( String sch) {
        return userDao.findByTime(sch);
    }

    @Override
    public User findByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

}
