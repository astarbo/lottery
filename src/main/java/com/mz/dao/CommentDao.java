package com.mz.dao;

import com.mz.domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mz
 * @Description：
 * @date 2018/6/26
 * @time 18:40
 */
public interface CommentDao extends JpaRepository<Comment,Integer> {
}
