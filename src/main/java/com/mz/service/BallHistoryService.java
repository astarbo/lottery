package com.mz.service;

import com.mz.domain.BallHistory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author mz
 * @Description：
 * @date 2018/6/23
 * @time 17:00
 */
public interface BallHistoryService {
    List<BallHistory> findAll();

    Page<BallHistory> findAll(Pageable pageable);

    BallHistory findOne(String code);
}
