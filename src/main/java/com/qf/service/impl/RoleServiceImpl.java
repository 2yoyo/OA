package com.qf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.dao.IBaseDao;
import com.qf.dao.RoleMapper;
import com.qf.entity.Role;
import com.qf.service.IRoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService{

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	protected IBaseDao<Role> getBaseDao() {
		return roleMapper;
	}

}
