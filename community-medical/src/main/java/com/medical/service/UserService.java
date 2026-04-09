package com.medical.service;

import com.medical.entity.User;

import java.util.List;

public interface UserService {
    User login(String username, String password);
    boolean register(User user);
    boolean updateUser(User user);
    boolean deleteUser(Integer id);
    User getUserById(Integer id);
    List<User> getAllUsers();
}
