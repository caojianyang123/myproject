package com.medical.service.impl;

import com.medical.entity.Appointment;
import com.medical.mapper.AppointmentMapper;
import com.medical.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public List<Appointment> getAppointmentsByUserId(Integer userId) {
        return appointmentMapper.selectByUserId(userId);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorId(Integer doctorId) {
        return appointmentMapper.selectByDoctorId(doctorId);
    }

    @Override
    public boolean checkAppointmentConflict(Integer userId, Integer doctorId, String appointmentDate) {
        List<Appointment> appointments = appointmentMapper.checkConflict(userId, doctorId, appointmentDate);
        return !appointments.isEmpty();
    }

    @Override
    public boolean createAppointment(Appointment appointment) {
        return appointmentMapper.insert(appointment) > 0;
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        return appointmentMapper.update(appointment) > 0;
    }

    @Override
    public boolean deleteAppointment(Integer id) {
        return appointmentMapper.deleteById(id) > 0;
    }

    @Override
    public Appointment getAppointmentById(Integer id) {
        return appointmentMapper.selectById(id);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentMapper.selectAll();
    }
}
