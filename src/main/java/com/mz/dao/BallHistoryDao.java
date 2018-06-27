package com.mz.dao;

import com.mz.domain.BallHistory;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mz
 * @Description：
 * @date 2018/6/23
 * @time 16:59
 */
public interface BallHistoryDao extends JpaRepository<BallHistory,String> {

}
