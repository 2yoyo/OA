package com.qf.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.qf.entity.Leave;
import com.qf.entity.WorkFlow;
import com.qf.service.ILeaveService;
import com.qf.service.IWorkFlowService;
import com.qf.utils.SessionUtils;

@Service
public class WorkFlowServiceImpl implements IWorkFlowService {

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private ILeaveService leaveServiceImpl;

	@Autowired
	private TaskService taskService;

	@Override
	public List<Deployment> getDeploymentList() {
		return repositoryService.createDeploymentQuery().list();
	}

	@Override
	public List<ProcessDefinition> getProcessDefinitionList() {
		return repositoryService.createProcessDefinitionQuery().list();
	}

	@Override
	public void deleteDeploy(String id) {
		repositoryService.deleteDeployment(id);
	}

	@Override
	public InputStream findProcessPng(String id, String name) {
		return repositoryService.getResourceAsStream(id, name);
	}

	@Override
	public void addProcessDeploy(WorkFlow workFlow) {
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		deploymentBuilder.name(workFlow.getName());
		ZipInputStream zips = null;
		try {
			zips = new ZipInputStream(workFlow.getFile().getInputStream());
			deploymentBuilder.addZipInputStream(zips);
			deploymentBuilder.deploy();// 部署
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (zips != null) {
				try {
					zips.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void startProcessIns(Integer id) {

		// 1.根据请假单Id查询请假对象
		Leave leave = leaveServiceImpl.getById(id);

		// 2.获取启动流程的key
		String key = leave.getClass().getSimpleName(); // 获取类名

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", SessionUtils.getSessionUser().getUsername()); // session

		// 这个字段是存到工作流那边的
		String businessKey = key + "_" + id;

		// 3.启动请假流程
		runtimeService.startProcessInstanceByKey(key, businessKey, map);

		// 4.修改请假单的状态(从0-->1)
		Map<String, Integer> leaveMap = new HashMap<String, Integer>();
		leaveMap.put("id", id);
		leaveMap.put("state", 5);
		leaveServiceImpl.updateState(leaveMap);
	}

	@Override
	public List<Task> getMyTaskList(String username) {
		return taskService.createTaskQuery().taskAssignee(username).orderByTaskId().desc().list();
	}

	@Override
	public Leave getLeaveByTaskId(String taskId) {

		// 1.根据taskid查询流程实例Id
		String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();

		// 2.根据流程实例Id得到流程实例对象
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();

		// 3.得到业务字段(实体类名称_ID)
		String businessKey = processInstance.getBusinessKey();

		// 4.解析字符串得到请假单ID
		Integer leaveId = Integer.parseInt(businessKey.split("_")[1]);

		// 5.根据请假单Id查询请假单对象
		return leaveServiceImpl.getById(leaveId);
	}

	@Override
	public List<String> getLinkListByTaskId(String taskId) {

		List<String> list = new ArrayList<String>();
		// 1.根据taskid查询流程实例Id
		String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();

		// 2.根据流程实例Id得到流程实例对象
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();

		// 3.得到流程定义Id
		String processDefinitionId = processInstance.getProcessDefinitionId();

		// 4.得到流程定义对象
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(processDefinitionId);

		// 5.活动id
		String activityId = processInstance.getActivityId();

		// 6.活动对象
		ActivityImpl activityImpl = processDefinition.findActivity(activityId);

		List<PvmTransition> outgoingTransitions = activityImpl.getOutgoingTransitions();
		for (PvmTransition pvmTransition : outgoingTransitions) {
			Object property = pvmTransition.getProperty("name");
			if (property != null) {
				list.add(property.toString());
			}
		}

		return list;
	}

	@Override
	public List<Comment> getCommentListByTaskId(String taskId) {
		// 1.根据taskid查询流程实例Id
		String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();

		// 2.根据流程实例Id查询批注对象
		List<Comment> processInstanceComments = taskService.getProcessInstanceComments(processInstanceId);

		return processInstanceComments;
	}

	@Override
	public void completeTask(WorkFlow workFlow) {
		String taskId = workFlow.getTaskId();

		// 1.根据taskid查询流程实例Id
		String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();

		// 2.添加批注信息
		Authentication.setAuthenticatedUserId(SessionUtils.getSessionUser().getUsername()); // 添加批注人
		taskService.addComment(taskId, processInstanceId, workFlow.getComment());

		// 3.完成我的任务
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", workFlow.getFlag());
		taskService.complete(taskId, map);

		// 4.修改请假单状态(如果流程实例查不到说明是流程走完了，就可以修改请假单状态)
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();

		Map<String, Integer> leaveMap = new HashMap<String, Integer>();
		leaveMap.put("id", workFlow.getLeaveId());
		Integer state = null;
		if (processInstance == null) {
			if ("同意".equals(workFlow.getFlag())) {
				state = 3;
			} else {
				state = 4;
			}
		} else {
			Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
			switch (task.getName()) {
			case "讲师审批":
				state = 1;
				break;
			case "班主任审批":
				state = 2;
				break;
			case "学生提交":
				state = 5;
				break;
			}

		}
		leaveMap.put("state", state);
		leaveServiceImpl.updateState(leaveMap);
	}

}
