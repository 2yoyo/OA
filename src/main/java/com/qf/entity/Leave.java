package com.qf.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Leave {
  
    private Integer id;

    private Integer empId;

    private Integer days;

    private String reason;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    private Integer state;
    
    private String username;

}