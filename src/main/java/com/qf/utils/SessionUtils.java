package com.qf.utils;

import org.apache.shiro.SecurityUtils;

import com.qf.entity.Emp;

public class SessionUtils {

	public static Emp getSessionUser() {
//		Emp emp = new Emp();
//		emp.setId(6);
//		emp.setUsername("admin");
//		emp.setMgr(6);
		Emp emp = (Emp)SecurityUtils.getSubject().getPrincipal();
		return emp;
	}
}
