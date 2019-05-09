package com.qf.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.entity.Page;
import com.qf.dao.IBaseDao;
import com.qf.entity.Role;
import com.qf.service.IBaseService;

public abstract class BaseServiceImpl<T> implements IBaseService<T>{

	protected abstract IBaseDao<T> getBaseDao(); // ?需要子类实例化
	
	@Override
	public int add(T t) {
		return getBaseDao().insertSelective(t);
	}

	@Override
	public int delete(Integer id) {
		return getBaseDao().deleteByPrimaryKey(id);
	}

	@Override
	public T getById(Integer id) {
		return getBaseDao().selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<T> getPage(Page<T> page,T t) {
		Integer currentPage = page.getCurrentPage();
		Integer pageSize = page.getPageSize();
	
		// 1.设置分页参数
		PageHelper.startPage(currentPage,pageSize);
		
		// 2.查询当前数据
		List<T> pageHelp = getBaseDao().getList(t);
		
		// 3.page转成PageInfo
		return new PageInfo<>(pageHelp);
	}

	@Override
	public int update(T t) {
		return getBaseDao().updateByPrimaryKeySelective(t);
	}
	
	@Override
	public int batchDel(Integer[] ids, String table) {
		return getBaseDao().batchDel(ids,table);
	}	
	
	
	@Override
	public List<Role> getAll() {
		return getBaseDao().getAll();
	}

}
