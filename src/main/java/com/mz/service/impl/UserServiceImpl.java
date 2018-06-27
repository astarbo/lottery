package com.mz.service.impl;

import com.mz.dao.UserDao;
import com.mz.domain.User;
import com.mz.service.UserService;
import com.mz.utils.MD5Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mz
 * @Description：
 * @date 2018/6/23
 * @time 21:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao mUserDao;

    @Override
    public void save(User user) {
        user.setPassword(MD5Util.encode(user.getPassword()));
        mUserDao.save(user);
    }

    @Override
    public User findOne(String email, String password) {
        return mUserDao.findByEmailAndPassword(email,MD5Util.encode(password));
    }
}
