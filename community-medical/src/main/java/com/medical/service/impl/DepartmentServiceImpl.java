package com.medical.service.impl;

import com.medical.entity.Department;
import com.medical.mapper.DepartmentMapper;
import com.medical.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public boolean createDepartment(Department department) {
        return departmentMapper.insert(department) > 0;
    }

    @Override
    public boolean updateDepartment(Department department) {
        return departmentMapper.update(department) > 0;
    }

    @Override
    public boolean deleteDepartment(Integer id) {
        return departmentMapper.deleteById(id) > 0;
    }

    @Override
    public Department getDepartmentById(Integer id) {
        return departmentMapper.selectById(id);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.selectAll();
    }
}
