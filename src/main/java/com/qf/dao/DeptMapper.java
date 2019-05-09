package com.qf.dao;

import java.util.List;

import com.qf.entity.Dept;

public interface DeptMapper extends IBaseDao<Dept> {

	List<Dept> getParentDeptList(Integer id);
}