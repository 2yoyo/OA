package com.qf.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.entity.Page;
import com.qf.dao.IBaseDao;
import com.qf.dao.MenuMapper;
import com.qf.entity.Menu;
import com.qf.service.IMenuService;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService{

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	protected IBaseDao<Menu> getBaseDao() {
		return menuMapper;
	}

	@Override
	public List<Menu> getParentMenuList(Integer id) {
		return menuMapper.getParentMenuList(id);
	}

	@Override
	public PageInfo<Menu> getMenuPageByRoleId(Page<Menu> page, Integer id) {
		PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
		return new PageInfo<Menu>(menuMapper.getMenuPageByRoleId(id));
	}

	@Override
	public List<Menu> getMenuListByEmpId(Integer id) {
		return menuMapper.getMenuListByEmpId(id);
	}

	@Override
	public Set<String> getPerCodeByEmpId(Integer id) {
		return menuMapper.getPerCodeByEmpId(id);
	}

}
