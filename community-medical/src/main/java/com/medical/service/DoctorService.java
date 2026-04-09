package com.medical.service;

import com.medical.entity.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getDoctorsByDepartmentId(Integer departmentId);
    boolean createDoctor(Doctor doctor);
    boolean updateDoctor(Doctor doctor);
    boolean deleteDoctor(Integer id);
    Doctor getDoctorById(Integer id);
    List<Doctor> getAllDoctors();
}
