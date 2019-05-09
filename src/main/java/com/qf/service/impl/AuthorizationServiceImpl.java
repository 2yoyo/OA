package com.qf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.dao.IAuthorizationMapper;
import com.qf.service.IAuthorizationService;

@Service
public class AuthorizationServiceImpl implements IAuthorizationService {

	@Autowired
	private IAuthorizationMapper authorizationMapper;
	
	@Override
	public int roleToEmp(Integer roleId, Integer[] empIds) {
		return authorizationMapper.roleToEmp(roleId, empIds);
	}

	@Override
	public List<Map<String, Object>> getMenuzTree(Integer roleId) {
		return authorizationMapper.getMenuzTree(roleId);
	}

	@Override
	public int deleteAuthorization(Integer id, Integer roleId) {
		return authorizationMapper.deleteAuthorization(id,roleId);
	}

	@Override
	public int deleteMenu(Integer id, Integer roleId) {
		return authorizationMapper.deleteMenu(id,roleId);
	}

	@Override
	public int batchAdd(Integer[] ids,Integer roleId) {
		return authorizationMapper.batchAdd(ids,roleId);
	}

	@Override
	public int remove(Integer roleId) {
		return authorizationMapper.remove(roleId);
	}
	
}
