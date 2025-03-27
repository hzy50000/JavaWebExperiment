package com.andy.messageboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.andy.messageboard.pojo.User;

@Mapper
public interface UserMapper {
    public List<User> getUserList();
    public User getUserById(Long id);
    public User getUserByUsername(String username);
    public int insertUser(User user);
    public int updateUser(User user);
    public int deleteUserById(Long id);
    public int deleteUserByIds(Long[] ids);
    public int updatePassword(User user);
}
