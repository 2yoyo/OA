package com.qf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.dao.DeptMapper;
import com.qf.dao.IBaseDao;
import com.qf.entity.Dept;
import com.qf.service.IDeptService;

@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements IDeptService{
	
	@Autowired
	private DeptMapper deptMapper;

	@Override
	protected IBaseDao<Dept> getBaseDao() {
		return deptMapper;
	}

	@Override
	public List<Dept> getParentDeptList(Integer id) {
		return deptMapper.getParentDeptList(id);
	}

	

}
