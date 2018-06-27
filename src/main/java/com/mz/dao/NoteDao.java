package com.mz.dao;

import com.mz.domain.Note;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mz
 * @Description：
 * @date 2018/6/24
 * @time 23:21
 */
public interface NoteDao extends JpaRepository<Note,String> {
    /**
     * 查询最新的帖子
     * @return
     */
    Note findTopByOrderByTimeDesc();
}
