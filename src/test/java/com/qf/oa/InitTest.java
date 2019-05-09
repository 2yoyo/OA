package com.qf.oa;


import javax.sql.DataSource;

import org.activiti.engine.TaskService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:applicationContext.xml")
public class InitTest {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private TaskService taskService;
	
	@Test
	public void testTaskService(){
		System.out.println(taskService);
	}
	
	@Test
	public void test() {
		System.err.println(dataSource.getClass());
	}
	
	@Test
	public void test1() {
		System.out.println(sqlSessionFactory);
	}
	

}
