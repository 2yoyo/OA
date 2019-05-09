package com.qf.service;

import java.io.InputStream;
import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import com.qf.entity.Leave;
import com.qf.entity.WorkFlow;

public interface IWorkFlowService {

	List<Deployment> getDeploymentList();

	List<ProcessDefinition> getProcessDefinitionList();

	void deleteDeploy(String id);

	InputStream findProcessPng(String id, String name);

	void addProcessDeploy(WorkFlow workFlow);

	void startProcessIns(Integer leaveId);

	List<Task> getMyTaskList(String username);

	Leave getLeaveByTaskId(String taskId);

	List<String> getLinkListByTaskId(String taskId);

	List<Comment> getCommentListByTaskId(String taskId);

	void completeTask(WorkFlow workFlow);

}
