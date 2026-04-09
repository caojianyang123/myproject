package com.medical.mapper;

import com.medical.entity.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DoctorMapper {
    List<Doctor> selectByDepartmentId(@Param("departmentId") Integer departmentId);
    int insert(Doctor doctor);
    int update(Doctor doctor);
    int deleteById(@Param("id") Integer id);
    Doctor selectById(@Param("id") Integer id);
    List<Doctor> selectAll();
}
