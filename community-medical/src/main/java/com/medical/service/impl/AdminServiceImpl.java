package com.medical.service.impl;

import com.medical.entity.Admin;
import com.medical.mapper.AdminMapper;
import com.medical.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String username, String password) {
        return adminMapper.login(username, password);
    }

    @Override
    public boolean createAdmin(Admin admin) {
        return adminMapper.insert(admin) > 0;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        return adminMapper.update(admin) > 0;
    }

    @Override
    public boolean deleteAdmin(Integer id) {
        return adminMapper.deleteById(id) > 0;
    }

    @Override
    public Admin getAdminById(Integer id) {
        return adminMapper.selectById(id);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminMapper.selectAll();
    }
}
