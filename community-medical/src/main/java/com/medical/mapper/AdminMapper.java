package com.medical.mapper;

import com.medical.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    Admin login(@Param("username") String username, @Param("password") String password);
    int insert(Admin admin);
    int update(Admin admin);
    int deleteById(@Param("id") Integer id);
    Admin selectById(@Param("id") Integer id);
    List<Admin> selectAll();
}
