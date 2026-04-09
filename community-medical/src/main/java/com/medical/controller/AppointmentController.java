package com.medical.controller;

import com.medical.entity.Appointment;
import com.medical.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/create")
    public Map<String, Object> createAppointment(@RequestBody Appointment appointment, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        com.medical.entity.User user = (com.medical.entity.User) session.getAttribute("user");
        if (user != null) {
            appointment.setUserId(user.getId());
            appointment.setStatus(0);
            // 检查预约冲突
            boolean hasConflict = appointmentService.checkAppointmentConflict(
                    user.getId(), appointment.getDoctorId(), appointment.getAppointmentDate().toString());
            if (hasConflict) {
                result.put("code", 400);
                result.put("message", "该时间已预约，请选择其他时间");
                return result;
            }
            boolean success = appointmentService.createAppointment(appointment);
            if (success) {
                result.put("code", 200);
                result.put("message", "预约成功");
            } else {
                result.put("code", 400);
                result.put("message", "预约失败");
            }
        } else {
            result.put("code", 400);
            result.put("message", "未登录");
        }
        return result;
    }

    @GetMapping("/my")
    public Map<String, Object> getMyAppointments(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        com.medical.entity.User user = (com.medical.entity.User) session.getAttribute("user");
        if (user != null) {
            List<Appointment> appointments = appointmentService.getAppointmentsByUserId(user.getId());
            result.put("code", 200);
            result.put("data", appointments);
        } else {
            result.put("code", 400);
            result.put("message", "未登录");
        }
        return result;
    }

    @PutMapping("/cancel/{id}")
    public Map<String, Object> cancelAppointment(@PathVariable Integer id, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        com.medical.entity.User user = (com.medical.entity.User) session.getAttribute("user");
        if (user != null) {
            Appointment appointment = appointmentService.getAppointmentById(id);
            if (appointment != null && appointment.getUserId().equals(user.getId())) {
                appointment.setStatus(2);
                boolean success = appointmentService.updateAppointment(appointment);
                if (success) {
                    result.put("code", 200);
                    result.put("message", "取消成功");
                } else {
                    result.put("code", 400);
                    result.put("message", "取消失败");
                }
            } else {
                result.put("code", 400);
                result.put("message", "预约不存在或无权限操作");
            }
        } else {
            result.put("code", 400);
            result.put("message", "未登录");
        }
        return result;
    }

    @GetMapping("/list")
    public Map<String, Object> getAllAppointments() {
        Map<String, Object> result = new HashMap<>();
        List<Appointment> appointments = appointmentService.getAllAppointments();
        result.put("code", 200);
        result.put("data", appointments);
        return result;
    }

    @PutMapping("/confirm/{id}")
    public Map<String, Object> confirmAppointment(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment != null) {
            appointment.setStatus(1);
            boolean success = appointmentService.updateAppointment(appointment);
            if (success) {
                result.put("code", 200);
                result.put("message", "确认成功");
            } else {
                result.put("code", 400);
                result.put("message", "确认失败");
            }
        } else {
            result.put("code", 400);
            result.put("message", "预约不存在");
        }
        return result;
    }
}
