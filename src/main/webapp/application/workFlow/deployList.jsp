<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=request.getContextPath() +"/"%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="4">流程部署信息表</th>
			</tr>
			<tr class="text-c">
				<th width="40">部署ID</th>
				<th width="50">部署名称</th>
				<th width="50">部署时间</th>
				<th width="150">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${deployments}" var="deployment">
			<tr class="text-c">
				<td>${deployment.id}</td>
				<td>${deployment.name}</td>
				<td>${deployment.deploymentTime}</td>
				<td>
					<a href="#">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<div class="pd-20">
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="8">流程定义信息表</th>
			</tr>
			<tr class="text-c">
				<th width="50">流程定义ID</th>
				<th width="100">名称</th>
				<th width="80">流程定义Key</th>
				<th width="50">流程定义版本</th>
				<th width="150">流程定义规则文件名称</th>
				<th width="100">流程定义规则图片名名</th>
				<th width="100">流程定义部署ID</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${definitions}" var="definition">
			<tr class="text-c">
				<td>${definition.id}</td>
				<td>${definition.name}</td>
				<td>${definition.key}</td>
				<td>${definition.version}</td>
				<td>${definition.resourceName}</td>
				<td>${definition.diagramResourceName}</td>
				<td>${definition.deploymentId}</td>
				<td>
					<a target="_blank" href="workFlowController/findProcessPng?id=${definition.deploymentId}&name=${definition.diagramResourceName}">查询流程图</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

<div class="pd-20">
		<form action="workFlowController/addProcessDeploy" method="post" class="form form-horizontal" id="form-member-add" enctype="multipart/form-data">
			<div class="row cl">
				<label class="form-label col-1">流程名称：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" placeholder=""
						name="name" datatype="*" nullmsg="流程文件名称不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-1">流程定义文件：</label>
				<span class="btn-upload form-group"> 
				
					<input class="input-text upload-url" type="text" name="uploadfile-2"
						id="uploadfile-2" readonly style="width: 200px"> 
						
						<a href="javascript:void();" class="btn btn-primary radius upload-btn"><i
							class="Hui-iconfont">&#xe642;</i> 浏览文件
						</a> 
						
					<input type="file" name="file" class="input-file">
					
				</span>
			</div>
			
			<div class="row cl">
				<div class="col-1 col-offset-1">
					<input class="btn btn-primary radius" type="submit"
						value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>  
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script type="text/javascript" src="js/oa_utils.js"></script> 
</body>
</html>