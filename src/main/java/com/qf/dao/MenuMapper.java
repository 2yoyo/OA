package com.qf.dao;

import java.util.List;
import java.util.Set;

import com.qf.entity.Menu;

public interface MenuMapper extends IBaseDao<Menu> {

	List<Menu> getParentMenuList(Integer id);

	List<Menu> getMenuPageByRoleId(Integer id);

	List<Menu> getMenuListByEmpId(Integer id);

	Set<String> getPerCodeByEmpId(Integer id);

}