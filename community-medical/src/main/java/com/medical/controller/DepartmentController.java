package com.medical.controller;

import com.medical.entity.Department;
import com.medical.entity.Doctor;
import com.medical.service.DepartmentService;
import com.medical.service.DoctorService;
import com.medical.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public Map<String, Object> getDepartmentList() {
        Map<String, Object> result = new HashMap<>();
        List<Department> departments = departmentService.getAllDepartments();
        result.put("code", 200);
        result.put("data", departments);
        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getDepartmentById(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        Department department = departmentService.getDepartmentById(id);
        if (department != null) {
            result.put("code", 200);
            result.put("data", department);
        } else {
            result.put("code", 400);
            result.put("message", "科室不存在");
        }
        return result;
    }

    @PostMapping("/add")
    public Map<String, Object> addDepartment(@RequestBody Department department) {
        Map<String, Object> result = new HashMap<>();
        boolean success = departmentService.createDepartment(department);
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
    public Map<String, Object> updateDepartment(@RequestBody Department department) {
        Map<String, Object> result = new HashMap<>();
        boolean success = departmentService.updateDepartment(department);
        if (success) {
            result.put("code", 200);
            result.put("message", "更新成功");
        } else {
            result.put("code", 400);
            result.put("message", "更新失败");
        }
        return result;
    }

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteDepartment(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 先获取该科室下的所有医生
            List<Doctor> doctors = doctorService.getDoctorsByDepartmentId(id);
            
            // 删除这些医生的所有预约记录
            for (Doctor doctor : doctors) {
                // 获取该医生的所有预约
                List<com.medical.entity.Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor.getId());
                // 逐个删除预约
                for (com.medical.entity.Appointment appointment : appointments) {
                    appointmentService.deleteAppointment(appointment.getId());
                }
            }
            
            // 删除该科室下的所有医生
            for (Doctor doctor : doctors) {
                doctorService.deleteDoctor(doctor.getId());
            }
            
            // 最后删除科室
            boolean success = departmentService.deleteDepartment(id);
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
