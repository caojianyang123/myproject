package com.medical.controller;

import com.medical.entity.User;
import com.medical.service.UserService;
import com.medical.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            session.setAttribute("user", loginUser);
            result.put("code", 200);
            result.put("message", "登录成功");
            result.put("user", loginUser);
        } else {
            result.put("code", 400);
            result.put("message", "用户名或密码错误");
        }
        return result;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        boolean success = userService.register(user);
        if (success) {
            result.put("code", 200);
            result.put("message", "注册成功");
        } else {
            result.put("code", 400);
            result.put("message", "注册失败");
        }
        return result;
    }

    @GetMapping("/info")
    public Map<String, Object> getInfo(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            result.put("code", 200);
            result.put("user", user);
        } else {
            result.put("code", 400);
            result.put("message", "未登录");
        }
        return result;
    }

    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody User user, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser != null) {
            user.setId(currentUser.getId());
            boolean success = userService.updateUser(user);
            if (success) {
                session.setAttribute("user", userService.getUserById(user.getId()));
                result.put("code", 200);
                result.put("message", "更新成功");
            } else {
                result.put("code", 400);
                result.put("message", "更新失败");
            }
        } else {
            result.put("code", 400);
            result.put("message", "未登录");
        }
        return result;
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        session.removeAttribute("user");
        result.put("code", 200);
        result.put("message", "登出成功");
        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getUserById(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.getUserById(id);
        if (user != null) {
            result.put("code", 200);
            result.put("data", user);
        } else {
            result.put("code", 400);
            result.put("message", "用户不存在");
        }
        return result;
    }

    @GetMapping("/list")
    public Map<String, Object> getUserList() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", userService.getAllUsers());
        return result;
    }

    @PutMapping("/disable/{id}")
    public Map<String, Object> disableUser(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.getUserById(id);
        if (user != null) {
            user.setStatus(0);
            boolean success = userService.updateUser(user);
            if (success) {
                result.put("code", 200);
                result.put("message", "禁用成功");
            } else {
                result.put("code", 400);
                result.put("message", "禁用失败");
            }
        } else {
            result.put("code", 400);
            result.put("message", "用户不存在");
        }
        return result;
    }

    @PutMapping("/enable/{id}")
    public Map<String, Object> enableUser(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.getUserById(id);
        if (user != null) {
            user.setStatus(1);
            boolean success = userService.updateUser(user);
            if (success) {
                result.put("code", 200);
                result.put("message", "启用成功");
            } else {
                result.put("code", 400);
                result.put("message", "启用失败");
            }
        } else {
            result.put("code", 400);
            result.put("message", "用户不存在");
        }
        return result;
    }

    @Autowired
    private AppointmentService appointmentService;

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteUser(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 先删除该用户的所有预约记录
            List<com.medical.entity.Appointment> appointments = appointmentService.getAppointmentsByUserId(id);
            // 逐个删除预约
            for (com.medical.entity.Appointment appointment : appointments) {
                appointmentService.deleteAppointment(appointment.getId());
            }
            
            // 然后删除用户
            boolean success = userService.deleteUser(id);
            if (success) {
                result.put("code", 200);
                result.put("message", "删除成功");
            } else {
                result.put("code", 400);
                result.put("message", "删除失败");
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "删除失败: " + e.getMessage());
        }
        return result;
    }
}
