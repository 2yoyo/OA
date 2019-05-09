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
	<form class="form form-horizontal" id="form-leave-add">
		
		<div class="row cl">
			<label class="form-label col-3">请假天数:</label>
			<div class="formControls col-5">
				<input type="text" placeholder="天数" name="days" autocomplete="off" value="${leave.days}" 
				class="input-text"  readonly="readonly">
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3">请假理由:</label>
			<div class="formControls col-5">
				<textarea name="reason"  readonly="readonly" cols="" rows="" class="textarea"  placeholder="说点什么...100个字符以内" dragonfly="true" onKeyUp="textarealength(this,100)">${leave.reason}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
			</div>
			<div class="col-4"> </div>
		</div>
	</form>
</div>
<div class="pd-20">
	<form class="form form-horizontal" id="form-leave-add" method="post" action="workFlowController/completeTask">
		
		<div class="row cl">
			<label class="form-label col-3">批注信息</label>
			<div class="formControls col-5">
				<input type="hidden" name="taskId" value="${taskId}">
				<input type="hidden" name="leaveId" value="${leave.id}">
				<textarea name="comment" cols="" rows="" class="textarea"  placeholder="说点什么...100个字符以内" dragonfly="true" onKeyUp="textarealength(this,100)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
			</div>
			<div class="col-4"> </div>
		</div>
		
			<div class="row cl">
			<div class="col-9 col-offset-3">
				<c:forEach items="${lineList}" var="line">
					<input class="btn btn-primary radius" name="flag" type="submit" value="${line}">
				</c:forEach>
			</div>
		</div>
	</form>
</div>

<div class="pd-20">
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="5">批注信息列表</th>
			</tr>
			<tr class="text-c">
				<th width="40">任务ID</th>
				<th width="50">批注信息</th>
				<th width="50">处理时间</th>
				<th width="50">办理人</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${comments}" var="commnet">
			<tr class="text-c">
				<td>${commnet.id}</td>
				<td>${commnet.fullMessage}</td>
				<td>${commnet.time}</td>
				<td>${commnet.userId}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
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