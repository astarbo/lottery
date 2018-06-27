package com.mz.service;

import com.mz.domain.Order;

import java.util.List;

/**
 * @author mz
 * @Description：
 * @date 2018/6/27
 * @time 17:01
 */
public interface OrderService {
    void save(Order order);

    List<Order> findByUid(String uid);
}
