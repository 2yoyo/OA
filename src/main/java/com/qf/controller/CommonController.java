package com.qf.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qf.entity.Emp;
import com.qf.entity.Menu;
import com.qf.service.IMenuService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

@Controller
public class CommonController {

	@Autowired
	private IMenuService menuService;
	
	@RequestMapping(value = "/toLogin")
	public String toLogin() {
		return "login";
	}

	@RequestMapping(value = "/login")
	public String login(String username, String password,ModelMap map) {
		
		
		Subject subject = SecurityUtils.getSubject();
		
		// 1.判断用户是否登陆
		if (!subject.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try {
				subject.login(token);
			} catch (AuthenticationException e) {
				System.err.println("认证失败");
				return "login";
			}
		}
		
		// 2.获取当前登陆的用户
		Emp emp= (Emp)SecurityUtils.getSubject().getPrincipal();
		
		// 3.根据userId查询他自己的菜单
		List<Menu> menus = menuService.getMenuListByEmpId(emp.getId());
		
		map.put("menus", menus);
		return "index";
	}
}
