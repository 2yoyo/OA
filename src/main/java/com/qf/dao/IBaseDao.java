package com.qf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qf.entity.Role;


public interface IBaseDao<T> {

	int deleteByPrimaryKey(Integer id);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);

	public List<T> getList(T t);

	int batchDel(@Param("ids")Integer[] ids, @Param("table_name")String table);

	List<Role> getAll();
}
