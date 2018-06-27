package com.mz.service.impl;

import com.mz.dao.BallHistoryDao;
import com.mz.domain.BallHistory;
import com.mz.service.BallHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mz
 * @Description：
 * @date 2018/6/23
 * @time 17:01
 */
@Service
public class BallHistoryServiceImpl implements BallHistoryService {

    @Autowired
    private BallHistoryDao mBallHistoryDao;

    @Override
    public List<BallHistory> findAll(){
        return mBallHistoryDao.findAll();
    }

    @Override
    public BallHistory findOne(String code) {
        return mBallHistoryDao.findOne(code);
    }

    @Override
    public Page<BallHistory> findAll(Pageable pageable) {
        Page<BallHistory> page = mBallHistoryDao.findAll(pageable);
        return page;
    }
}
