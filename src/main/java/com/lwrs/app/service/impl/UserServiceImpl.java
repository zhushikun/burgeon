package com.lwrs.app.service.impl;

import com.lwrs.app.db.entity.User;
import com.lwrs.app.db.mapper.UserMapper;
import com.lwrs.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override public List<User> getAllUser() {
        return userMapper.selectById(null);
    }
}
