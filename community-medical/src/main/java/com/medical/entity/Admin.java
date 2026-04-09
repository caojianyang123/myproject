package com.medical.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Date createTime;
}
