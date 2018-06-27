package com.mz.service.impl;

import com.mz.dao.OrderDao;
import com.mz.domain.Order;
import com.mz.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mz
 * @Description：
 * @date 2018/6/27
 * @time 17:01
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao mOrderDao;

    @Override
    public void save(Order order) {
        mOrderDao.save(order);
    }

    @Override
    public List<Order> findByUid(String uid) {
        return mOrderDao.findByUid(uid);
    }
}
