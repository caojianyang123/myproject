package com.medical.mapper;

import com.medical.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User login(@Param("username") String username, @Param("password") String password);
    int insert(User user);
    int update(User user);
    int deleteById(@Param("id") Integer id);
    User selectById(@Param("id") Integer id);
    List<User> selectAll();
}
