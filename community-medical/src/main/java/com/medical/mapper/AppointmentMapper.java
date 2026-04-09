package com.medical.mapper;

import com.medical.entity.Appointment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppointmentMapper {
    List<Appointment> selectByUserId(@Param("userId") Integer userId);
    List<Appointment> selectByDoctorId(@Param("doctorId") Integer doctorId);
    List<Appointment> checkConflict(@Param("userId") Integer userId, 
                                     @Param("doctorId") Integer doctorId, 
                                     @Param("appointmentDate") String appointmentDate);
    int insert(Appointment appointment);
    int update(Appointment appointment);
    int deleteById(@Param("id") Integer id);
    Appointment selectById(@Param("id") Integer id);
    List<Appointment> selectAll();
}
