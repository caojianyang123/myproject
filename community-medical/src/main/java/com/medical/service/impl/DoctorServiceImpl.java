package com.medical.service.impl;

import com.medical.entity.Doctor;
import com.medical.mapper.DoctorMapper;
import com.medical.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public List<Doctor> getDoctorsByDepartmentId(Integer departmentId) {
        return doctorMapper.selectByDepartmentId(departmentId);
    }

    @Override
    public boolean createDoctor(Doctor doctor) {
        return doctorMapper.insert(doctor) > 0;
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        return doctorMapper.update(doctor) > 0;
    }

    @Override
    public boolean deleteDoctor(Integer id) {
        return doctorMapper.deleteById(id) > 0;
    }

    @Override
    public Doctor getDoctorById(Integer id) {
        return doctorMapper.selectById(id);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorMapper.selectAll();
    }
}
