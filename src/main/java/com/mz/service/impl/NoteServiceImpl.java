package com.mz.service.impl;

import com.mz.dao.NoteDao;
import com.mz.domain.Note;
import com.mz.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mz
 * @Description：
 * @date 2018/6/24
 * @time 23:22
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao mNoteDao;

    @Override
    public List<Note> findAll() {
        return mNoteDao.findAll();
    }

    @Override
    public void save(Note note) {
        mNoteDao.save(note);
    }

    @Override
    public Note findNew() {
        return mNoteDao.findTopByOrderByTimeDesc();
    }

    @Override
    public Note findOne(String id) {
        return mNoteDao.findOne(id);
    }
}
