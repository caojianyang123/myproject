package com.medical.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Department {
    private Integer id;
    private String name;
    private String description;
    private Date createTime;
}
