package com.mz.service;

import com.mz.domain.User;

/**
 * @author mz
 * @Description：
 * @date 2018/6/23
 * @time 21:18
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    void save(User user);

    /**
     * 邮箱和密码验证是否存在该用户
     * @param email
     * @param password
     * @return
     */
    User findOne(String email, String password);

    void update(User user);
}
