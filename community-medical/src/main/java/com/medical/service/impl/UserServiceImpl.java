package com.medical.service.impl;

import com.medical.entity.User;
import com.medical.mapper.UserMapper;
import com.medical.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }

    @Override
    public boolean register(User user) {
        user.setStatus(1);
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.update(user) > 0;
    }

    @Override
    public boolean deleteUser(Integer id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }
}
