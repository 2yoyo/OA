package com.qf.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.qf.entity.Page;
import com.qf.entity.Role;

public interface IBaseService<T> {

	public int add(T t);

	public int update(T t);

	public int delete(Integer id);

	public T getById(Integer id);

	public PageInfo<T> getPage(Page<T> page,T t);

	public int batchDel(Integer[] ids, String string);
	
	List<Role> getAll();
}
