package com.mz.service.impl;

import com.mz.dao.CommentDao;
import com.mz.domain.Comment;
import com.mz.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mz
 * @Description：
 * @date 2018/6/26
 * @time 18:40
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    /**
     * 新增评论
     */
    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }
}
