package com.medical.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Appointment {
    private Integer id;
    private Integer userId;
    private Integer doctorId;
    private Date appointmentDate;
    private String description;
    private Integer status;
    private Date createTime;
    
    private User user;
    
    private Doctor doctor;
}
