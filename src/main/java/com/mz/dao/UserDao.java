package com.mz.dao;

import com.mz.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mz
 * @Description：
 * @date 2018/6/23
 * @time 21:19
 */
public interface UserDao extends JpaRepository<User,String> {
    User findByEmailAndPassword(String email, String password);
}
