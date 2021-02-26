package com.zstao.mapper;

import com.zstao.entity.UserLike;

import java.util.List;

/**
 * 用户关注
 */
public interface UserLikeDao {
    List<UserLike> listUserLikes(); //用户关注用户

    UserLike getUserLike(Integer likeId);

    List<UserLike> listUserNotLikes(); // 用户取消关注的用户

    int saveUserLike(UserLike userLike);

    int updateUserLike(Integer likeId, Integer status);  //用户取消关注

    UserLike queryByUser(Integer user_id,Integer author_id);

    int userLikeNumber();

    int getUserLikeNumber(Integer userId);  //用户关注的数量

    int getUserBeLikeNumber(Integer authorId);  //用户被关注的数量

    List<String> listUserLikeUsers(Integer userId);  // 用户关注了哪些人

    List<String> listUserBeLikeUsers(Integer authorId); //用户被哪些人关注
}
