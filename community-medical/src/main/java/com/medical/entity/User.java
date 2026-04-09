package com.medical.entity;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String idCard;
    private Integer status;
    private Date createTime;
}
