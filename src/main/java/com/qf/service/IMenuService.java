package com.qf.service;

import java.util.List;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.qf.entity.Page;
import com.qf.entity.Menu;

public interface IMenuService extends IBaseService<Menu> {

	List<Menu> getParentMenuList(Integer id);

	PageInfo<Menu> getMenuPageByRoleId(Page<Menu> page, Integer id);

	List<Menu> getMenuListByEmpId(Integer id);

	Set<String> getPerCodeByEmpId(Integer id);
}
