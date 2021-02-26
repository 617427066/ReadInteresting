package com.zstao.service;

import com.zstao.entity.UserLike;

public interface UserLikeService extends BaseService<UserLike>{
    int getUserBeLikeNumber(Integer author_id);

    UserLike queryByUser(Integer userid,Integer aid);

    int getUserLikeNumber(Integer user_id);
}
