package com.zstao.service;

import com.zstao.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    List<Comment> findByStatus(Integer status);

    Comment getComment(Integer id);

    boolean deleteComment(Integer id);

    boolean addComment(Comment comment);

    List<Comment> findByUser(Integer userid); //

    List<Comment> findByArticle(Integer aid);



}
