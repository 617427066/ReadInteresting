package com.zstao.service.serviceImpl;

import com.zstao.entity.UserLike;
import com.zstao.mapper.UserLikeDao;
import com.zstao.service.UserLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserLikeServiceImpl implements UserLikeService {
    @Resource
    private UserLikeDao userLikeDao;
    @Override
    public int add(UserLike userLike) {
        return userLikeDao.saveUserLike(userLike);
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int update(UserLike userLike) {
        return 0;
    }

    @Override
    public UserLike findOne(int id) {
        return null;
    }

    @Override
    public List<UserLike> findAll() {
        return null;
    }


    @Override
    public int getUserBeLikeNumber(Integer author_id) {
        return userLikeDao.getUserBeLikeNumber(author_id);
    }

    @Override
    public UserLike queryByUser(Integer userid, Integer aid) {
        return userLikeDao.queryByUser(userid,aid);
    }

    @Override
    public int getUserLikeNumber(Integer user_id) {
        return userLikeDao.getUserLikeNumber(user_id);
    }
}
