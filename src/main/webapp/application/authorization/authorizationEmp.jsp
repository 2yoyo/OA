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
<div class="pd-20">
		<form action="authorizationController/getEmpPage" method="post">
			<div class="text-c">
				<div class="row cl ">
					<div class="formControls col-3">
					<input type="hidden" id="roleId" value="${roleId}"/>
						登录名: <input type="text" name="username" value="${emp.username}" class="input-text" style="width: 250px">
					</div>
				</div>
				<div class="row cl">
					<div class="cl pd-5">
						<button type="submit" 
							class="btn btn-success radius" id="" name="">
							<i class="Hui-iconfont">&#xe665;</i> 搜用户
						</button>
						
						<button type="reset" onclick="javascript:location.replace(location.href);" 
							class="btn btn-success radius" id="" name="">
							<i class="Hui-iconfont">&#xe68f;</i> 重置
						</button>
						
						<button type="reset" onclick="authorizationRoleToEmp()" 
							class="btn btn-success radius" id="" name="">
							<i class="Hui-iconfont">&#xe676;</i> 授权
						</button>
					</div>
				</div>
			</div>
		</form>
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="40">ID</th>
				<th width="150">用户名</th>
				<th width="90">手机</th>
				<th width="100">部门名称</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="emp">
			<tr class="text-c">
				<td><input type="checkbox" value="${emp.id}" name=""></td>
				<td>${emp.id}</td>
				<td>${emp.username}</td>
				<td>${emp.phone}</td>
				<td>${emp.dname}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<jsp:include page="/common/page.jsp"></jsp:include>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>  
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script type="text/javascript" src="js/oa_utils.js"></script> 
<script type="text/javascript">

function authorizationRoleToEmp(){
	
	//获取角色Id
	var roleId = $("#roleId").val();
	
	//获取用户选中的Id
	var empIdArray = new Array();
	$(":checkbox:checked").each(function(index,item){
		empIdArray.push(item.value);
	});
	
	//创建对象	
	var param = new Object();
	param.roleId = roleId;
	param.empIds = empIdArray;
	
	//发送数据到后台
	$.post("authorizationController/roleToEmp",param,function(data){
		showMsg(data);
	},"JSON");
}


</script>
</body>
</html>