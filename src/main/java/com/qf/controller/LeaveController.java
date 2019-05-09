package com.qf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qf.entity.Leave;
import com.qf.service.IBaseService;
import com.qf.service.ILeaveService;

@Controller
@RequestMapping(value="/leaveController")
public class LeaveController extends BaseController<Leave> {

	@Autowired
	private ILeaveService leaveService;

	@Override
	protected IBaseService<Leave> getBasService() {
		return leaveService;
	}
	

}
