package com.qf.service;

import java.util.List;

import com.qf.entity.Dept;

public interface IDeptService extends IBaseService<Dept> {

	List<Dept> getParentDeptList(Integer id);

}
