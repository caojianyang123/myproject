package com.medical.controller;

import com.medical.entity.Doctor;
import com.medical.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/list")
    public Map<String, Object> getDoctorList() {
        Map<String, Object> result = new HashMap<>();
        List<Doctor> doctors = doctorService.getAllDoctors();
        result.put("code", 200);
        result.put("data", doctors);
        return result;
    }

    @GetMapping("/listByDepartment/{departmentId}")
    public Map<String, Object> getDoctorsByDepartment(@PathVariable Integer departmentId) {
        Map<String, Object> result = new HashMap<>();
        List<Doctor> doctors = doctorService.getDoctorsByDepartmentId(departmentId);
        result.put("code", 200);
        result.put("data", doctors);
        return result;
    }

    @PostMapping("/add")
    public Map<String, Object> addDoctor(@RequestBody Doctor doctor) {
        Map<String, Object> result = new HashMap<>();
        boolean success = doctorService.createDoctor(doctor);
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
    public Map<String, Object> updateDoctor(@RequestBody Doctor doctor) {
        Map<String, Object> result = new HashMap<>();
        boolean success = doctorService.updateDoctor(doctor);
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
    public Map<String, Object> deleteDoctor(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        boolean success = doctorService.deleteDoctor(id);
        if (success) {
            result.put("code", 200);
            result.put("message", "删除成功");
        } else {
            result.put("code", 400);
            result.put("message", "删除失败");
        }
        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getDoctorById(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            result.put("code", 200);
            result.put("data", doctor);
        } else {
            result.put("code", 400);
            result.put("message", "医生不存在");
        }
        return result;
    }
}
