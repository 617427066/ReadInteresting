package com.zstao.service.serviceImpl;

import com.zstao.entity.Comment;
import com.zstao.mapper.CommentDao;
import com.zstao.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;
    @Override
    public List<Comment> findAll() {
        return commentDao.listComments() ;
    }

    @Override
    public List<Comment> findByStatus(Integer status) {
        return commentDao.listByStatus(status);
    }

    @Override
    public Comment getComment(Integer id) {
        return commentDao.getComment(id);
    }

    @Override
    public boolean deleteComment(Integer id) {
        int i = commentDao.deleteComment(id);
        if(i>0){
            return true;
        }else{

        return false;
        }

    }

    @Override
    public boolean addComment(Comment comment) {
        int i = commentDao.saveComment(comment);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Comment> findByUser(Integer userid) {
        List<Comment> list = commentDao.queryByUser(userid);
        return list;
    }

    @Override
    public List<Comment> findByArticle(Integer aid) {

        return commentDao.queryByArticle(aid);
    }
}
