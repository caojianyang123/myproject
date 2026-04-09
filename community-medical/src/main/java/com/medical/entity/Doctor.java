package com.medical.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Doctor {
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String title;
    private Integer departmentId;
    private String description;
    private Date createTime;
    
    private Department department;
}
