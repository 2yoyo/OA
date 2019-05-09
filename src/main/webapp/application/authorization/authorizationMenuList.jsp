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
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="40">ID</th>
				<th width="150">菜单名</th>
				<th width="90">菜单描述</th>
				<th width="90">菜单url</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="menu">
			<tr class="text-c">
				<td><input type="checkbox" value="${menu.id}" name=""></td>
				<td>${menu.id}</td>
				<td>${menu.menuName}</td>
				<td>${menu.menuDesc}</td>
				<td>${menu.menuUrl}</td>
				<td><a title="删除" href="javascript:;" onclick="admin_del(this,${menu.id})" class="ml-5" style="text-decoration:none">
					<i class="Hui-iconfont">&#xe6e2;</i></a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
<%-- 	<jsp:include page="/common/page.jsp"/> --%>
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
	
	//获取用户Id
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

function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		debugger;
		var roleId = $("#roleId").val(); 
		
		$.get("authorizationController/deleteMenu/"+id+"/"+roleId,"",function(data){
			showMsg(data);
 		},"JSON");
	});
}

</script>
</body>
</html>