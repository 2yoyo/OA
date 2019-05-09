package com.qf.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Emp {
    private Integer id;

    private String username;

    private String password;

    private Integer sex;

    private String email;

    private String phone;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date hiredate;

    private Integer deptid;
    
    private String dname;
    
    private Integer mgr;

}