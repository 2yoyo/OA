package com.qf.service;

import com.github.pagehelper.PageInfo;
import com.qf.entity.Page;
import com.qf.entity.Emp;

public interface IEmpService extends IBaseService<Emp> {

	PageInfo<Emp> getEmpPageByRoleId(Page<Emp> page, Integer id);

	Emp getEmpByUsername(String username);

}
