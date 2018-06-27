package com.mz.dao;

import com.mz.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author mz
 * @Description：
 * @date 2018/6/27
 * @time 17:00
 */
public interface OrderDao extends JpaRepository<Order,String> {
    /**
     * 根据用户id查询所有的订单
     * @param uid
     * @return
     */
    List<Order> findByUid(String uid);
}
