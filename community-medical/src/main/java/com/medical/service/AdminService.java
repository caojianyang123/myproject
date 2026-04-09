package com.medical.service;

import com.medical.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin login(String username, String password);
    boolean createAdmin(Admin admin);
    boolean updateAdmin(Admin admin);
    boolean deleteAdmin(Integer id);
    Admin getAdminById(Integer id);
    List<Admin> getAllAdmins();
}
