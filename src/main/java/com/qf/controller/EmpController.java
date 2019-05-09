package com.qf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qf.entity.Emp;
import com.qf.service.IBaseService;
import com.qf.service.IEmpService;

@Controller
@RequestMapping(value="/empController")
public class EmpController extends BaseController<Emp>{
	
	@Autowired
	private IEmpService empService;

	@Override
	protected IBaseService<Emp> getBasService() {
		return empService;
	}
	
	@RequestMapping(value="/batchDel",produces="text/html;charset=utf8")
	@ResponseBody
	@Override
	public String batchDel(@RequestParam("ids[]")Integer[] ids) {
		return responceClient(getBasService().batchDel(ids, "t_emp"));
	}
}
