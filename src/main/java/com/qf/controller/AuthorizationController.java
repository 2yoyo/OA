package com.qf.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.qf.entity.Page;
import com.qf.entity.Emp;
import com.qf.entity.Menu;
import com.qf.entity.Role;
import com.qf.service.IAuthorizationService;
import com.qf.service.IBaseService;
import com.qf.service.IEmpService;
import com.qf.service.IMenuService;
import com.qf.service.IRoleService;

@Controller
@RequestMapping(value="/authorizationController")
public class AuthorizationController extends BaseController<Object> {

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IAuthorizationService authorizationService;
	
	@Autowired
	private IEmpService empService;
	
	@Autowired
	private IMenuService menuService;
	
	@RequestMapping(value="/toAuthorizationList")
	public String toAuthorizationList(ModelMap map){
		List<Role> roles = roleService.getAll();
		map.put("roleAll", roles);
		return "authorization/authorization";
	}
	
	@RequestMapping(value="/getEmpPage/{roleId}")
	public String getEmpPage(@PathVariable Integer roleId,Emp emp,Page<Emp> page,ModelMap map){
		PageInfo<Emp> pageInfo = empService.getPage(page,emp);
		map.put("page", pageInfo);
		map.put("url",super.getPageUrl(emp));
		map.put("roleId", roleId);
		return "authorization/authorizationEmp";
	}

	@Override
	protected IBaseService<Object> getBasService() {
		return null;
	}
	
	@RequestMapping(value="/roleToEmp",produces="text/html;charset=utf8")
	@ResponseBody
	public String roleToEmp(Integer roleId,@RequestParam("empIds[]") Integer[] empIds){
		int roleToEmp = 0;
		try {
			roleToEmp = authorizationService.roleToEmp(roleId, empIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responceClient(roleToEmp);
	}
	
	
	@RequestMapping(value="/getMenuzTree/{roleId}",produces="text/html;charset=utf8")
	@ResponseBody
	public String getMenuzTree(@PathVariable Integer roleId){
		List<Map<String, Object>> menus = authorizationService.getMenuzTree(roleId);
		return toJson(menus);
	}
	
	
	@RequestMapping(value="/getEmpPageByRoleId/{id}")
	public String getEmpPageByRoleId(Page<Emp> page,@PathVariable Integer id,ModelMap map){
		PageInfo<Emp> pageInfo = empService.getEmpPageByRoleId(page,id);
		map.put("page", pageInfo);
		map.put("url", "authorizationController/getEmpPageByRoleId/"+id+"?");
		return "authorization/authorizationEmpList";
	}
	
	@RequestMapping(value="/getMenuPageByRoleId/{id}")
	public String getMenuPageByRoleId(Page<Menu> page,@PathVariable Integer id,ModelMap map){
		PageInfo<Menu> pageInfo =  menuService.getMenuPageByRoleId(page,id);
		map.put("page", pageInfo);
		map.put("url", "authorizationController/getMenuPageByRoleId/"+id+"?");
		return "authorization/authorizationMenuList";
	}
	
	@RequestMapping(value="/deleteAuthorization/{id}/{roleId}",produces="text/html;charset=utf8")
	@ResponseBody
	public String deleteAuthorization(@PathVariable Integer id,@PathVariable Integer roleId){
		int deleteAuthorization = authorizationService.deleteAuthorization(id,roleId);
		return responceClient(deleteAuthorization);
	}
	
	
	@RequestMapping(value="/deleteMenu/{id}/{roleId}",produces="text/html;charset=utf8")
	@ResponseBody
	public String deleteMenu(@PathVariable Integer id,@PathVariable Integer roleId){
		int deleteMenu = authorizationService.deleteMenu(id,roleId);
		return responceClient(deleteMenu);
	}
	
	@RequestMapping(value="/batchAdd",produces="text/html;charset=utf8")
	@ResponseBody
	public String batchAdd(@RequestParam("ids[]")Integer[] ids,Integer roleId){
		authorizationService.remove(roleId);
		int batchAdd = authorizationService.batchAdd(ids,roleId);
		return responceClient(batchAdd);
	}
}

