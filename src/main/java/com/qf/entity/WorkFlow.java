package com.qf.entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class WorkFlow {

	private String name;
	
	private MultipartFile file;
	
	private String taskId;
	
	private String flag; // 同意/驳回
	
	private String comment; // 批注信息
	
	private Integer leaveId; // 请假单Id
}
