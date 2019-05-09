package com.qf.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.qf.entity.Emp;

public interface EmpMapper extends IBaseDao<Emp> {

	List<Emp> getEmpPageByRoleId(Integer id);

	Emp getEmpByUsername(String username);
	
}