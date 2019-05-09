package com.qf.service.impl;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.qf.entity.Page;
import com.qf.entity.Dept;
import com.qf.entity.Emp;
import com.qf.service.IAuthorizationService;
import com.qf.service.IDeptService;
import com.qf.service.IEmpService;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:applicationContext.xml")
public class DeptServiceImplTest {

	@Autowired
	private IDeptService deptService;
	
	@Autowired
	private IEmpService empService;
	
	@Autowired
	private IAuthorizationService authorizationService;

	
	@Test
	public void testRoleToEmp(){
		Integer[] empIds = {7,6,14};
		System.out.println(authorizationService.roleToEmp(1, empIds));
	}
	
	@Test
	public void testAdd() {
		Dept t = new Dept();
		t.setDname("千峰教育");
		t.setDdesc("拼搏到感动自己");
		t.setDindex(1);
		t.setDparentid(0);
		t.setDstate(1);
		System.out.println(deptService.add(t));
	}
	
	@Test
	public void testAdd1() {
		Dept t = new Dept();
		t.setDname("Java教学部");
		t.setDdesc("教学部desc");
		t.setDindex(1);
		t.setDparentid(2);
		t.setDstate(1); // 1:可用，0：不可用
		System.out.println(deptService.add(t));
	}

	
	

	@Test
	public void testDelete() {
	}

	@Test
	public void testGetById() {
	}

	@Test
	public void testGetPage() {
		Page<Emp> page = new Page<Emp>();
		Emp emp = new Emp();
		emp.setUsername("a");
		PageInfo<Emp> pageInfo = empService.getPage(page,emp);
		System.out.println(pageInfo);
		List<Emp> list = pageInfo.getList();
		for (Emp dept : list) {
			System.out.println(dept);
		}
	}

	@Test
	public void testUpdate() {
	}

}
