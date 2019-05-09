<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=request.getContextPath() +"/"%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 资讯管理 <span class="c-gray en">&gt;</span> 资讯列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
	<div class="text-c">
		<label class="form-label col-1">
			<span class="c-red"></span>
				角色名称:
		</label>
	
		 <span class="select-box inline col-2">
			<select name="" id="roleId" class="select">
					<option value="">==请选择==</option>
				<c:forEach items="${roleAll}" var="role">
					<option value="${role.id}">${role.roleName}</option>
				</c:forEach>
			</select>
		</span>
		
	<label class="form-label col-1">
		授权类型:
	</label>
	 <span class="select-box inline col-3">
		<select id="queryType" name="" class="select">
			<option value="">==请选择==</option>
			<option value="1">用户</option>
			<option value="2">菜单</option>
		</select>
	</span>
		<button name="" id="" class="btn btn-success" type="button" onclick="queryAuthorization()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
		<span class="l">
			<a class="btn btn-primary radius" onclick="authorizationEmp()" href="javascript:;">
				<i class="Hui-iconfont">&#xe600;</i> 授权人员
			</a>
			 
			<a class="btn btn-primary radius" onclick="authorizationMenu()" href="javascript:;">
				<i class="Hui-iconfont">&#xe600;</i> 授权菜单
			</a>
		</span> 
	</div>
	<div class="mt-10">
		<table id="tableList" class="table table-border table-bordered table-bg table-hover table-sort">
		</table>
	</div>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="js/oa_utils.js"></script>
<script type="text/javascript">
	
	//授权人员
	function authorizationEmp(){
		debugger;
		var roleId = isRoleId();
		if(roleId){
			//打开一个Emp展示窗口
			layer_show("选择人员","authorizationController/getEmpPage/"+roleId,"800","500")
		}
	}
	
	//授权菜单
	function authorizationMenu(){
		var roleId = isRoleId();
		if(roleId){
			// 1.打开一个框
			layer.open({
				type : 2,
				title : false,
				closeBtn : 2,
				area: ['400px','500px'],
				shadeClose : true,
				skin : 'yourclass',
				content : 'application/authorization/tree.jsp?param='+roleId
			});
		}
	}
	
	//公共的判断是否选择了角色的方法
	function isRoleId(){
		//判断用户是否选择角色
		debugger;
		var roleId = $("#roleId").val();
		
		if(roleId == ""){
			alert("请选择角色再进行操作");
			return roleId;
		}
		return roleId;
	}
	
	
	function queryAuthorization(){
		debugger;
		//获取角色Id
		var roleId = $("#roleId").val();
		
		//获取查询类型
		var queryType = $("#queryType").val();
		
		//做非空判断
		if(roleId == ""){
			alert("请选择角色");
			return false;
		}
		if(queryType == ""){
			alert("请选择查询类型");
			return false;
		}
		
		//判断用户查询的类型
		if(queryType =="1"){
			$("#tableList").load("authorizationController/getEmpPageByRoleId/"+roleId);
		}else{
			$("#tableList").load("authorizationController/getMenuPageByRoleId/"+roleId);
		}
		
	}
</script> 
</body>
</html>