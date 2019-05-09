package com.qf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qf.entity.Role;
import com.qf.service.IBaseService;
import com.qf.service.IRoleService;

@Controller
@RequestMapping(value="/roleController/")
public class RoleController extends BaseController<Role> {

	@Autowired
	private IRoleService roleService;
	
	@Override
	protected IBaseService<Role> getBasService() {
		return roleService;
	}

}
