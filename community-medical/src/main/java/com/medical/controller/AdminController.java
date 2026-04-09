package com.medical.controller;

import com.medical.entity.Admin;
import com.medical.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Admin admin, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Admin loginAdmin = adminService.login(admin.getUsername(), admin.getPassword());
        if (loginAdmin != null) {
            session.setAttribute("admin", loginAdmin);
            result.put("code", 200);
            result.put("message", "登录成功");
            result.put("admin", loginAdmin);
        } else {
            result.put("code", 400);
            result.put("message", "用户名或密码错误");
        }
        return result;
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        session.removeAttribute("admin");
        result.put("code", 200);
        result.put("message", "登出成功");
        return result;
    }

    @GetMapping("/info")
    public Map<String, Object> getInfo(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            result.put("code", 200);
            result.put("admin", admin);
        } else {
            result.put("code", 400);
            result.put("message", "未登录");
        }
        return result;
    }

    @GetMapping("/list")
    public Map<String, Object> getAdminList() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", adminService.getAllAdmins());
        return result;
    }

    @PostMapping("/add")
    public Map<String, Object> addAdmin(@RequestBody Admin admin) {
        Map<String, Object> result = new HashMap<>();
        boolean success = adminService.createAdmin(admin);
        if (success) {
            result.put("code", 200);
            result.put("message", "添加成功");
        } else {
            result.put("code", 400);
            result.put("message", "添加失败");
        }
        return result;
    }

    @PutMapping("/update")
    public Map<String, Object> updateAdmin(@RequestBody Admin admin) {
        Map<String, Object> result = new HashMap<>();
        boolean success = adminService.updateAdmin(admin);
        if (success) {
            result.put("code", 200);
            result.put("message", "更新成功");
        } else {
            result.put("code", 400);
            result.put("message", "更新失败");
        }
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteAdmin(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        boolean success = adminService.deleteAdmin(id);
        if (success) {
            result.put("code", 200);
            result.put("message", "删除成功");
        } else {
            result.put("code", 400);
            result.put("message", "删除失败");
        }
        return result;
    }
}
