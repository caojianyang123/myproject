package com.medical.service;

import com.medical.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAppointmentsByUserId(Integer userId);
    List<Appointment> getAppointmentsByDoctorId(Integer doctorId);
    boolean checkAppointmentConflict(Integer userId, Integer doctorId, String appointmentDate);
    boolean createAppointment(Appointment appointment);
    boolean updateAppointment(Appointment appointment);
    boolean deleteAppointment(Integer id);
    Appointment getAppointmentById(Integer id);
    List<Appointment> getAllAppointments();
}
