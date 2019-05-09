package com.qf.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Role {
    private Integer id;
    
    private String roleName;

    private String roleDesc;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

}