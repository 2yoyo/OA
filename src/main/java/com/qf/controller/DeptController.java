package com.qf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qf.entity.Dept;
import com.qf.service.IBaseService;
import com.qf.service.IDeptService;

@Controller
@RequestMapping(value="/deptController/")
public class DeptController extends BaseController<Dept> {

	@Autowired
	private IDeptService deptService;
	
	@Override
	protected IBaseService<Dept> getBasService() {
		return deptService;
	}
	
	@ResponseBody
	@RequestMapping(value="/getParentDeptList",produces="text/html;charset=utf8")
	public String getParentDeptList(Integer id){
		
		//判断是否传入id,如果没有就查最大的部门
		if(id == null){
			id = 0;
		}
		//查询所有的部门
		List<Dept> deptList = deptService.getParentDeptList(id);
		
		//创建集合
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (Dept dept : deptList) {
			
			//把部门信息装到map中
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("id", dept.getId());
			map.put("pid", dept.getDparentid());
			map.put("name", dept.getDname());
			map.put("isParent", dept.getSubId() != null?true:false);
			
			//把map装入到list集合中
			list.add(map);
		}
		
		//把list转成json数据
		return toJson(list);
	}
	
}
