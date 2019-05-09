package com.qf.listener;

import java.awt.GradientPaint;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qf.entity.Emp;
import com.qf.service.IEmpService;
import com.qf.service.impl.EmpServiceImpl;
import com.qf.utils.SessionUtils;

public class NextAssignee implements TaskListener {

//	@Autowired 因为NextAssignee不是Spring管理员，所以这里写自动注入是不起作用的
//	private IEmpService EmpService;;
	
	@Override
	public void notify(DelegateTask task) {
		
		
		// 1.获取ServletContext
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		ServletContext servletContext = request.getServletContext();
		
		// 2.获取Spring容器对象
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		
		// 3.获取容器中获取empService
		IEmpService empService = ctx.getBean(IEmpService.class);
		
		// 4.得到当前登陆用户
		Emp emp = SessionUtils.getSessionUser();
		
		// 5.得到领导Id
		Integer mgr = emp.getMgr();

		// 6.根据领导id查询领导对象
		Emp manager = empService.getById(mgr);
		
		// 7.处理人
		task.setAssignee(manager.getUsername());
	}
}
