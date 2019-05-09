package com.qf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface IAuthorizationMapper {

	public int roleToEmp(@Param("roleId")Integer roleId,@Param("empIds")Integer[] empIds);

	public List<Map<String, Object>> getMenuzTree(Integer roleId);

	public int deleteAuthorization(@Param("id")Integer id, @Param("roleId")Integer roleId);

	public int deleteMenu(@Param("id")Integer id, @Param("roleId")Integer roleId);

	public int batchAdd(@Param("ids")Integer[] ids, @Param("roleId")Integer roleId);

	public int remove(Integer roleId);
}
