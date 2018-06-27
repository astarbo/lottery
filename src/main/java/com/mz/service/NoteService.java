package com.mz.service;

import com.mz.domain.Note;

import java.util.List;

/**
 * @author mz
 * @Description：
 * @date 2018/6/24
 * @time 23:22
 */
public interface NoteService {
    List<Note> findAll();

    void save(Note note);

    Note findNew();

    Note findOne(String id);
}
