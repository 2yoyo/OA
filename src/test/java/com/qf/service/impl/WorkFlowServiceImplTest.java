package com.qf.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qf.service.IWorkFlowService;
import com.sun.tools.javah.resources.l10n;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class WorkFlowServiceImplTest {

	@Autowired
	private IWorkFlowService workFlowService;

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	HistoryService historyService;

	@Autowired
	TaskService taskService;

	@Test
	public void historyComment() {
		String leaveId = "11";
		String businessKey = "Leave_" + leaveId;
		
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceBusinessKey(businessKey).singleResult();
		
		String superProcessInstanceId = historicProcessInstance.getSuperProcessInstanceId();
		String id = historicProcessInstance.getId();
		
		List<Comment> comments = taskService.getProcessInstanceComments(id);
		for (Comment comment : comments) {
			System.out.println(comment.getFullMessage());
		}
		System.out.println(comments.size());
	}

	@Test
	public void testGetDeploymentList() {
		List<Deployment> deploymentList = workFlowService.getDeploymentList();
		for (Deployment deployment : deploymentList) {
			System.out.println(deployment.getName());
		}

		List<ProcessDefinition> processDefinitionList = workFlowService.getProcessDefinitionList();
		for (ProcessDefinition processDefinition : processDefinitionList) {
			processDefinition.getId();
			processDefinition.getName();
			processDefinition.getKey();
			processDefinition.getVersion();
			processDefinition.getResourceName();
			processDefinition.getDiagramResourceName();
			processDefinition.getDeploymentId();
		}
	}

	@Test
	public void testProcessDeploy() {

		// 3.创建一个部署对象
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();

		// 4.设置部署的资源
		// deploymentBuilder.addClasspathResource("hello4.bpmn");
		// deploymentBuilder.addClasspathResource("hello4.png");
		ZipInputStream zipInputStream = null;
		try {
			zipInputStream = new ZipInputStream(new FileInputStream("C:/Users/asus/Desktop/Desktop.zip"));
			deploymentBuilder.addZipInputStream(zipInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				zipInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 5.设置部署的名称
		deploymentBuilder.name("请假流程图v4.0");

		// 6.部署操作
		deploymentBuilder.deploy();

	}
}
