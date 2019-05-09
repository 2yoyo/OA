package com.qf.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qf.entity.Leave;
import com.qf.entity.WorkFlow;
import com.qf.service.IWorkFlowService;
import com.qf.utils.SessionUtils;

@Controller
@RequestMapping(value = "workFlowController")
public class WorkFlowController {

	@Autowired
	private IWorkFlowService workFlowService;

	@RequestMapping(value = "/getDeployPage")
	public String getDeployPage(ModelMap map) {

		// 1.查询流程部署的集合
		List<Deployment> deployments = workFlowService.getDeploymentList();

		// 2.查询流程定义的集合
		List<ProcessDefinition> definitions = workFlowService.getProcessDefinitionList();

		map.put("deployments", deployments);
		map.put("definitions", definitions);

		return "workFlow/deployList";
	}

	@RequestMapping(value = "/deleteDeploy/{id}")
	public String deleteDeploy(@PathVariable String id) {
		workFlowService.deleteDeploy(id);
		return "redirect:../getDeployPage";
	}

	@RequestMapping(value = "/findProcessPng")
	public void findProcessPng(String id, String name, HttpServletResponse resp) {
		InputStream ips = workFlowService.findProcessPng(id, name);
		try {
			IOUtils.copy(ips, resp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/addProcessDeploy")
	public String addProcessDeploy(WorkFlow workFlow) {
		workFlowService.addProcessDeploy(workFlow);
		return "redirect:getDeployPage";
	}

	@RequestMapping(value = "/startProcessIns")
	public String startProcessIns(Integer id) {
		workFlowService.startProcessIns(id);
		return "redirect:getMyTaskList";
	}

	@RequestMapping(value = "/getMyTaskList")
	public String getMyTaskList(ModelMap map) {
		List<Task> taskList = workFlowService.getMyTaskList(SessionUtils.getSessionUser().getUsername());
		map.put("taskList", taskList);
		return "workFlow/taskList";
	}
	
	@RequestMapping(value="/getTaskInfoById")
	public String getTaskInfoById(String taskId,ModelMap map){
		
		// 1.查询请假单对象
		Leave leave = workFlowService.getLeaveByTaskId(taskId);
		
		// 2.连线集合
		List<String> lineList =workFlowService.getLinkListByTaskId(taskId);
		
		// 3.批注信息
		List<Comment> comments = workFlowService.getCommentListByTaskId(taskId);
		
		map.put("leave", leave);
		map.put("lineList", lineList);
		map.put("comments", comments);
		map.put("taskId", taskId);
		
		return "workFlow/taskInfo";
	}
	
	@RequestMapping(value="/completeTask")
	public String completeTask(WorkFlow workFlow){
		System.out.println(workFlow);
		workFlowService.completeTask(workFlow);
		return "redirect:getMyTaskList";
	}
}
