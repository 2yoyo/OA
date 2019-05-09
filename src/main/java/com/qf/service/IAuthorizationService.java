package com.qf.service;

import java.util.List;
import java.util.Map;

public interface IAuthorizationService {

	public int roleToEmp(Integer roleId,Integer[] empIds);

	public List<Map<String, Object>> getMenuzTree(Integer roleId);

	public int deleteAuthorization(Integer id, Integer roleId);

	public int deleteMenu(Integer id, Integer roleId);

	public int batchAdd(Integer[] ids,Integer roleId);

	public int remove(Integer roleId);
}
