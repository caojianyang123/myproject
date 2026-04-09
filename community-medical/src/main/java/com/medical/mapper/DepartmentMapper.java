package com.medical.mapper;

import com.medical.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    int insert(Department department);
    int update(Department department);
    int deleteById(@Param("id") Integer id);
    Department selectById(@Param("id") Integer id);
    List<Department> selectAll();
}
