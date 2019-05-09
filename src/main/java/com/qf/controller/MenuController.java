package com.qf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.qf.entity.Emp;
import com.qf.entity.Menu;
import com.qf.entity.Page;
import com.qf.service.IBaseService;
import com.qf.service.IMenuService;

@Controller
@RequestMapping(value="/menuController/")
public class MenuController extends BaseController<Menu> {

	@Autowired
	private IMenuService menuService;
	
	@Override
	protected IBaseService<Menu> getBasService() {
		return menuService;
	}
	
	@RequiresPermissions(value="user:getPage")
	@Override
	@RequestMapping(value = "/getPage")
	public String getPage(Menu menu,Page<Menu> page, ModelMap map) {

		// 1.封装分页对象
		PageInfo<Menu> pageInfo = getBasService().getPage(page,menu);

		// 2.把pageInfo放到Map中
		map.put("page", pageInfo);
		map.put("url", getPageUrl(menu)); // ?

		// 3.跳转到视图页面
		return "menu/menuList"; // User,userList.jsp,userAdd.jsp,userUdpate.jsp
	}
	
	@RequestMapping(value="/getParentMenuList",produces="text/html;charset=utf8")
	@ResponseBody
	public String getParentMenuList(Integer id){
		
		if(id == null){
			id = 0;
		}
		
		//查询所有部门
		List<Menu> menuList = menuService.getParentMenuList(id);
		
		//创建集合
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (Menu menu : menuList) {
			
			//信息装入map集合
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("id", menu.getId());
			map.put("pid", menu.getMenuParentid());
			map.put("name", menu.getMenuName());
			map.put("isParent", menu.getSubId() != null?true:false);
			
			list.add(map);
		}
		return toJson(list);
	}

}
