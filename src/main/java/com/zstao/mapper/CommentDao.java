package com.zstao.mapper;


import com.zstao.entity.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> listComments();

    Comment getComment(Integer cid);

    List<Comment> listByStatus(Integer status);

    int saveComment(Comment comment);

    int deleteComment(Integer cid);

    int commentNumber();

    List<Comment> queryByUser(Integer uid);

    List<Comment> queryByArticle(Integer aid);
}
