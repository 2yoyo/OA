package com.qf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.entity.Page;
import com.qf.dao.EmpMapper;
import com.qf.dao.IBaseDao;
import com.qf.entity.Emp;
import com.qf.service.IEmpService;

@Service
public class EmpServiceImpl extends BaseServiceImpl<Emp> implements IEmpService {

	@Autowired
	private EmpMapper empMapper;
	
	@Override
	protected IBaseDao<Emp> getBaseDao() {
		return empMapper;
	}

	@Override
	public PageInfo<Emp> getEmpPageByRoleId(Page<Emp> page,Integer id) {
		PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
		return new PageInfo<Emp>(empMapper.getEmpPageByRoleId(id));
	}

	@Override
	public Emp getEmpByUsername(String username) {
		return empMapper.getEmpByUsername(username);
	}

	

}
