package com.andy.messageboard.service;

import java.util.List;

import com.andy.messageboard.pojo.User;

public interface UserService {

    public User getUserById(Long id);
    public User getUserByUsername(String username);
    public int insertUser(User user);
    public int updateUser(User user);
    public int deleteUserById(Long id);
    public List<User> getAllUsers();
    public User login(String username, String password);
}
