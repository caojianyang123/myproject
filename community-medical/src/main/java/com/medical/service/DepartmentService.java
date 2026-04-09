package com.medical.service;

import com.medical.entity.Department;

import java.util.List;

public interface DepartmentService {
    boolean createDepartment(Department department);
    boolean updateDepartment(Department department);
    boolean deleteDepartment(Integer id);
    Department getDepartmentById(Integer id);
    List<Department> getAllDepartments();
}
