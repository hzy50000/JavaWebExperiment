package com.andy.messageboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.messageboard.mapper.UserMapper;
import com.andy.messageboard.pojo.User;
import com.andy.messageboard.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUserById(Long id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getUserList();
    }

    @Override
    public User login(String username, String password) {
        // 1. 根据用户名查询用户
        User user = userMapper.getUserByUsername(username);
        
        // 2. 如果用户不存在，返回null
        if (user == null) {
            return null;
        }
        
        // 3. 比对密码是否匹配
        if (password.equals(user.getPassword())) {
            // 密码匹配，返回用户信息
            return user;
        }
        
        // 4. 密码不匹配，返回null
        return null;
    }
}
